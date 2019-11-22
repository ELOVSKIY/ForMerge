package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.adamstyrc.cookiecutter.ImageUtils
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up_crop_photo_controller.*
import java.io.File
import java.io.FileOutputStream

class UserEditAuthCropAddPhotoController : EditController() {

    private val LOG_TAG: String = "UserEditAuthCropAddPhotoController"

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
        submitButton.setOnClickListener {
            save()
        }

    }

    override fun save(validate: Boolean) {
        if (validate) {
            val bitmap = imageViewCrop.croppedBitmap

            //TODO WTF
            val circularBitmap = ImageUtils.getCircularBitmap(bitmap)
            imageViewCrop.setImageBitmap(circularBitmap)
            val uriCircle = saveBitmapAndGetUri(circularBitmap)
            dataObject = User().createUserBuilder(dataObject as User)
                .setUser_PhotoCircleUri(uriCircle)
                .build()
            Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
            if (validate) {
                val profile = dataObject as User
                startEditOrViewActivity(SignUpCheckDataController::class.java, profile)
            }
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
        if (dataObject != null) {

            imageViewCrop.setImageURI((dataObject as User).getUserPhotoUri())
        }
    }


}
