package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.google.gson.Gson
import kotlinx.android.synthetic.main.controller_gender_auth_user.*

class UserEditAuthGenderController : EditController() {

    private val LOG_TAG: String = "UserEditAuthGenderController"
    private var gender: String = ""
    private var checking: Boolean = true

    private lateinit var radioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_gender_auth_user)

        checking = intent.getBooleanExtra("checking", true)

    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.registration))
        Log.d(LOG_TAG, "init")
        addTextView(resources.getString(R.string.step5), resources.getString(R.string.enter_sex))

        submitButton!!.setOnClickListener {
            save()
        }
        setDefaultValue()
    }

    override fun save(validate: Boolean) {
        if (validate) {
            dataObject = User().createUserBuilder(dataObject as User)
                .setUser_Gender(gender)
                .build()
            Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
            if (validate) {
                val profile = dataObject as User
                if (!checking) {
                    startEditOrViewActivity(SignUpCheckDataController::class.java, profile)
                } else {
                    startEditOrViewActivity(
                        UserEditAuthChoosePhotoFromGalleryController::class.java,
                        profile
                    )
                }
            }
        } else {
            Toast.makeText(this, R.string.error_gender, Toast.LENGTH_LONG).show()
        }
    }

    private fun setDefaultValue(){
        
    }


    @SuppressLint("LongLogTag")
    fun checkedGender(view: View) {
        when (view.id) {
            R.id.radio_men -> {
                gender = resources.getString(R.string.male)
                Log.d(LOG_TAG, gender)
            }
            R.id.radio_women -> {
                gender = resources.getString(R.string.female)
                Log.d(LOG_TAG, gender)
            }
        }
    }

    override fun load() {
        if (dataObject != null) {
            if ((dataObject as User).getUserGender() == R.string.male.toString()) {
                radio_men.isChecked = true
            } else {
                radio_women.isChecked = true
            }
        }
    }

    override fun validate(): Boolean {
        if (gender == "") {
            return false
        }
        return true
    }

}









