package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_sign_up_check_data_controller.*

class SignUpCheckDataController : EditController() {

    private val LOG_TAG: String = "UserEditAuthCropAddPhotoController"
    private var check: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_check_data_controller)
    }

    override fun setListeners() {
        setOnBackListener()
        setOnTakePhotoListener()
        setOnChangeNameListener()
        setOnChangeBirthDayListener()
        setOnChangeGenderListener()
    }

    private fun setOnChangeGenderListener(){
        changeGenderView.setOnClickListener {
            val profile = dataObject as User
            startEditOrViewActivityBoolean(UserEditAuthGenderController::class.java, profile, check)
        }
    }

    private fun setOnChangeBirthDayListener(){
        changeBirthdayView.setOnClickListener{
            val profile = dataObject as User
            startEditOrViewActivityBoolean(UserEditAuthDateOfBirthController::class.java, profile, check)
        }
    }

    private fun setOnChangeNameListener(){
        changeNameView.setOnClickListener {
            val profile = dataObject as User
            startEditOrViewActivityBoolean(UserEditAuthNameController::class.java, profile, check)
        }
    }

    private fun setOnTakePhotoListener(){
        button_take_photo.setOnClickListener {
            save()
        }
    }

    private fun setOnBackListener(){
        backButton.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("LongLogTag")
    override fun init() {

        super.init()
        setTitle(getString(R.string.adding_photo))
        Log.d(LOG_TAG, "init")
    }

    override fun save(validate: Boolean) {
        Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
        if (validate) {
            val profile = dataObject as User
            startEditOrViewActivity(UserEditAuthLastStepController::class.java, profile)
        }
    }

    override fun load() {
        if (dataObject != null) {

            Picasso.get()
                .load((dataObject as User).getUserPhotoUri())
                .placeholder(R.drawable.ic_cloud_download_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .fit()
                .centerCrop()
                .into(imageUser as ImageView)

            textViewName.text = ((dataObject as User).getUserName())
            textViewPhone.text = ((dataObject as User).getUserPhone())
            textViewDateOfBirth.text = ((dataObject as User).getUserDateOfBirth())
            textViewGender.text = ((dataObject as User).getUserGender())
        }
    }
}
