package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.controllers.mainScreen.UserMainScreenController
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController

class UserEditAuthLastStepController : EditController() {

    private val LOG_TAG: String = "UserEditAuthLastStepController"
    private var imageUser: ImageView? = null
    private var submitButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        hideActionBar()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_last_step_controller)

    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        Log.d(LOG_TAG, "init")
        imageUser = findViewById(R.id.imageUserCircle)
        submitButton = findViewById(R.id.button_total)
        submitButton?.setOnClickListener {
            save()
        }
    }

    override fun save(validate: Boolean) {
        val profile = dataObject as User
        startEditOrViewActivity(UserMainScreenController::class.java, profile)
    }

    override fun load() {
        if (dataObject != null) {
            imageUser?.setImageURI((dataObject as User).getUserPhotoCircleUri())
        }
    }

    fun hideActionBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}

