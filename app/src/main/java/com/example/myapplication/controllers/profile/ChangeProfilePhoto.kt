package com.example.myapplication.controllers.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.UserEditAuthChoosePhotoFromGalleryAdapter
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.controller_pick_photo_from_gallery.*
import android.provider.MediaStore
import android.content.Intent
import android.app.Activity
import android.graphics.Bitmap
import android.os.Environment
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File
import java.io.FileOutputStream


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class ChangeProfilePhoto : EditController(){

    private lateinit var profile: User

    private val LOG_TAG: String = "UserEditAuthChoosePhotoFromGalleryController"
    private val PERMISSION_WRITE_GALLERY = 1
    private var uri: Uri? = null
    private val imageUriList = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_pick_photo_from_gallery)
    }


    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.adding_photo))
        Log.d(LOG_TAG, "init")
        profile= dataObject as User

        processWritePermission()
        button_take_photo.setOnClickListener {
            takePhoto()
        }
    }

    private fun takePhoto() {
        ImagePicker.with(this)
                .cameraOnly()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
    }

    private fun processWritePermission() {
        if (!hasWritePermission())
            getWritePermission()
        else
            setRecyclerView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_WRITE_GALLERY -> {
                if (permissions.isNotEmpty()) {
                    if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                        setRecyclerView()
                    }
                }
            }
        }
    }

    private fun hasWritePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getWritePermission() {
        requestPermissions(
            this@ChangeProfilePhoto,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_WRITE_GALLERY
        )
    }
//EDITPROFILECONTROLLER

    private fun setRecyclerView() {
        val mediaStorageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = this.contentResolver.query(mediaStorageUri, projection, null, null, null)
        while (cursor!!.moveToNext()) {
            val absolutePathOfImage =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
            val imageUri = Uri.fromFile(File(absolutePathOfImage))
            imageUriList.add(imageUri)
        }
        imageUriList.reverse()
        val adapter =
                UserEditAuthChoosePhotoFromGalleryAdapter(
                        imageUriList
                )
        adapter.setOnItemClickListener(object: OnRecyclerItemClick {
            override fun onClick(pos: Int) {
                uri = imageUriList[pos]
                save()

            }
        })

        val layoutManager = GridLayoutManager(this, 3)
        galleryRecycler.adapter = adapter
        galleryRecycler.layoutManager = layoutManager
    }

    private fun saveBitmapAndGetUri(bitmap: Bitmap): Uri {
        val sdCard = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.getAbsolutePath() + "/DCIM/Camera")
        val fileName = String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, fileName)
        val outputStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return Uri.fromFile(outFile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            uri = data?.data
            save()
        }
    }

    override fun save(validate: Boolean) {
        if (validate) {
           startEditOrViewActivity(ProfileCropChangedPhoto::class.java, profile)
            finish()
        }
    }
}
