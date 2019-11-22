package com.example.myapplication.controllers.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.google.gson.Gson
import kotlinx.android.synthetic.main.controller_phone_code_auth_user.*

@SuppressLint("Registered")
class UserEditAuthPhoneCodeController : EditController() {

    private val LOG_TAG: String = "UserEditAuthPhoneCodeController"


    private var submitButton: Button? = null

    private var code: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_phone_code_auth_user)
    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.registration))
        Log.d(LOG_TAG, "init")
        addTextView(resources.getString(R.string.step2), resources.getString(R.string.code))

        setListeners()

        submitButton = findViewById(R.id.button_code)
        submitButton?.setOnClickListener {
            save()
        }
        openKeyboard()
    }

    private fun openKeyboard(){
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun setListeners() {
        setListenerOnEditText(editCode1)
        setListenerOnEditText(editCode2)
        setListenerOnEditText(editCode3)
        setListenerOnEditText(editCode4)
    }


    @SuppressLint("LongLogTag")
    private fun setListenerOnEditText(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d("LENGTH_PHONE", charSequence.toString().length.toString())
                if (charSequence.toString().length == 1) {
                    when {
                        view.id == R.id.editCode1 -> {
                            if (charSequence.isNotEmpty())
                                editCode2.requestFocus()
                        }
                        view.id == R.id.editCode2 -> {
                            if (charSequence.isNotEmpty())
                                editCode3.requestFocus()
                            else
                                editCode1.requestFocus()
                        }
                        view.id == R.id.editCode3 -> {
                            if (charSequence.isNotEmpty())
                                editCode4.requestFocus()
                            else
                                editCode2.requestFocus()
                        }
                        view.id == R.id.editCode4 -> {
                            if (charSequence.isEmpty())
                                editCode3.requestFocus()
                        }
                    }
                }
            }
            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        })
    }

    override fun save(validate: Boolean) {
        //Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
        code =
            editCode1.text.toString() + editCode2.text.toString() + editCode3.text.toString() + editCode4.text.toString()
        dataObject = User().createUserBuilder(dataObject as User)
            .setSms_Code_Value(code)
            .build()
        Log.d("USER_OBJECT", Gson().toJson(dataObject as User))
        if (validate) {
            val profile = dataObject as User
            startEditOrViewActivity(UserEditAuthNameController::class.java, profile)
        }
    }

    override fun load() {
    }

    override fun validate(): Boolean {
        if (editCode1.text.toString() == "" || editCode2.text.toString() == "" || editCode3.text.toString() == "" || editCode4.text.toString() == "") {
            Toast.makeText(this, resources.getString(R.string.enter_confirmation_code),
                Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
