package com.example.myapplication.controllers.auth

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.UserEditAuthChoosePhotoFromGalleryAdapter
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.controller_pick_photo_from_gallery.*
import android.content.Intent
import android.app.Activity
import android.provider.MediaStore
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class UserEditAuthChoosePhotoFromGalleryController : EditController(){


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
        processWritePermission()
        button_take_photo.setOnClickListener {
            takePhoto()
        }
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
                if (grantResults.isNotEmpty()) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
                this@UserEditAuthChoosePhotoFromGalleryController,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSION_WRITE_GALLERY
        )
    }

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
        adapter.setOnItemClickListener(object: OnRecyclerItemClick{
            override fun onClick(pos: Int) {
                uri = imageUriList[pos]
                save()

            }
        })

        val layoutManager = GridLayoutManager(this, 3)
        galleryRecycler.adapter = adapter
        galleryRecycler.layoutManager = layoutManager
    }

    private fun takePhoto() {
        ImagePicker.with(this)
                .cameraOnly()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
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
            dataObject = User().createUserBuilder(dataObject as User)
                    .setUser_PhotoUri(uri)
                    .build()
            val profile = dataObject as User
            startEditOrViewActivity(UserEditAuthCropAddPhotoController::class.java, profile)
        }
    }
}
