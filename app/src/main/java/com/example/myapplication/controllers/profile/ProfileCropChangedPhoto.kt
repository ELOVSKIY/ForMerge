package com.example.myapplication.controllers.profile

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.adamstyrc.cookiecutter.CookieCutterImageView
import com.adamstyrc.cookiecutter.ImageUtils
import com.example.myapplication.R
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.activity_sign_up_crop_photo_controller.*
import java.io.File
import java.io.FileOutputStream

class ProfileCropChangedPhoto : EditController() {

    private val LOG_TAG: String = "UserEditAuthCropAddPhotoController"

    lateinit var circleImageView: CookieCutterImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_crop_photo_controller)

    }

    @SuppressLint("LongLogTag")
    override fun init() {

        super.init()
        showToolbar()
        setTitle(getString(R.string.adding_photo))
        Log.d(LOG_TAG, "init")
        circleImageView = findViewById(R.id.imageViewCrop)
        submitButton.setOnClickListener {
            save()
        }

    }

    override fun save(validate: Boolean) {
        if (validate) {
            val bitmap = circleImageView.croppedBitmap
            val circularBitmap = ImageUtils.getCircularBitmap(bitmap)
            circleImageView.setImageBitmap(circularBitmap)
            EditProfileController.circlePhotoUri = saveBitmapAndGetUri(circularBitmap)
            finish()
        }
    }

    private fun saveBitmapAndGetUri(bitmap: Bitmap): Uri {
        val sdCard = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath + "/DCIM/Camera")
        val fileName = String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, fileName)
        val outputStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return Uri.fromFile(outFile)
    }

    override fun load() {
        if (EditProfileController.photoUri != null) {
            imageViewCrop.setImageURI((EditProfileController.photoUri))
        }
    }


}
