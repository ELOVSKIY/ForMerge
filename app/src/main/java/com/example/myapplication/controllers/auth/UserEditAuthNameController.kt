package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.google.gson.Gson
import kotlinx.android.synthetic.main.controller_name_auth_user.*

class UserEditAuthNameController : EditController() {

    private val LOG_TAG: String = "UserEditAuthNameController"
    private var checking: Boolean = true

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_name_auth_user)

        checking = intent.getBooleanExtra("checking", true)
        Log.d(LOG_TAG, checking.toString())
    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.registration))
        Log.d(LOG_TAG, "init")
        addTextView(resources.getString(R.string.step3),
            resources.getString(R.string.registration))

        edit_name.requestFocus()
        findViewById<Button>(R.id.submitButton)?.setOnClickListener {
                save()
        }
        openKeyboard()
    }

    private fun openKeyboard(){
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    @SuppressLint("LongLogTag")
    override fun save(validate: Boolean) {
        Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
        dataObject = User().createUserBuilder(dataObject as User)
            .setUser_Name(edit_name.text.toString())
            .build()
        Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
        if (validate) {
            val profile = dataObject as User
            if (!checking) {
                startEditOrViewActivity(SignUpCheckDataController::class.java, profile)
            } else {
                startEditOrViewActivity(UserEditAuthDateOfBirthController::class.java, profile)
            }
        }

    }

    override fun load() {
        if (dataObject != null) {
            edit_name.setText((dataObject as User).getUserName())
        }
    }

    override fun validate(): Boolean {
        if (edit_name.text.isBlank()) {
            Toast.makeText(this, R.string.error_name, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
