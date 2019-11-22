package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast

import com.example.myapplication.R
import com.example.myapplication.data.network.MyEventAPI
import com.example.myapplication.data.network.RetrofitProvider
import com.example.myapplication.data.network.request.PhonePostRequest
import com.example.myapplication.data.network.responses.PhonePostResponse
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.controller_phone_auth_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class UserEditAuthPhoneController : EditController() {

    private val LOG_TAG: String = "UserEditAuthPhoneController"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_phone_auth_user)
    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.registration))
        Log.d(LOG_TAG, "init")
        addTextView(resources.getString(R.string.step1), resources.getString(R.string.step1_desc))

        editPhone.requestFocus()
        openKeyboard()
    }

    private fun openKeyboard() {
        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun save(validate: Boolean) {
        if (validate) {
            dataObject = User().newUserBuilder()
                    .setUser_Phone(editPhone.text.toString())
                    .build()
            val profile = dataObject as User
            startEditOrViewActivity(UserEditAuthPhoneCodeController::class.java, profile)

        }
    }

    override fun setListeners() {
        submitButton.setOnClickListener {
            val phone = editPhone.text.toString()
            save()
        }
    }

    override fun load() {
        if (dataObject != null) {
            editPhone?.setText((dataObject as User).getUserPhone())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun validate(): Boolean {
        val phone = editPhone.text.toString()
        if (phone == "" || !phone.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}".toRegex())) {
            Toast.makeText(this, R.string.error_phone, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}

