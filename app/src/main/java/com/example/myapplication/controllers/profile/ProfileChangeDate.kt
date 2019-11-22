package com.example.myapplication.controllers.profile

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
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.controller_date_of_birth_auth_user.*

class ProfileChangeDate : EditController() {

    private val LOG_TAG: String = "UserEditAuthDateOfBirthController"

    private lateinit var submitButton: Button

    private var dateOfBirth: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.controller_date_of_birth_auth_user)
    }

    @SuppressLint("LongLogTag")
    override fun init() {
        super.init()
        showToolbar()
        setTitle(getString(R.string.registration))
        Log.d(LOG_TAG, "init")
        addTextView(
                resources.getString(R.string.step4),
                resources.getString(R.string.enterDateBirthday)
        )
        setStartState()
        setListeners()
        openKeyboard()

        submitButton.setOnClickListener {
            save()
        }
    }

    override fun setListeners() {
        textChange(editDay)
        textChange(editDay2)
        textChange(editMonth)
        textChange(editMonth2)
        textChange(editYear)
        textChange(editYear2)
        textChange(editYear3)
        textChange(editYear4)
    }

    private fun openKeyboard() {
        window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    @SuppressLint("LongLogTag")
    fun textChange(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d("LENGTH_PHONE", charSequence.toString().length.toString())
                if (charSequence.toString().length == 1) {
                    when (view.id) {
                        R.id.editDay -> {
                            editDay2.requestFocus()
                        }
                        R.id.editDay2 -> {
                            if (charSequence.isNotEmpty())
                                editMonth.requestFocus()
                            else
                                editDay.requestFocus()
                        }
                        R.id.editMonth -> {
                            if (charSequence.isNotEmpty())
                                editMonth2.requestFocus()
                            else
                                editDay2.requestFocus()
                        }
                        R.id.editMonth2 -> {
                            if (charSequence.isNotEmpty())
                                editYear.requestFocus()
                            else
                                editMonth.requestFocus()
                        }
                        R.id.editYear -> {
                            if (charSequence.isNotEmpty())
                                editYear2.requestFocus()
                            else
                                editMonth2.requestFocus()
                        }
                        R.id.editYear2 -> {
                            if (charSequence.isNotEmpty())
                                editYear3.requestFocus()
                            else
                                editYear.requestFocus()
                        }
                        R.id.editYear3 -> {
                            if (charSequence.isNotEmpty())
                                editYear4.requestFocus()
                            else
                                editYear2.requestFocus()
                        }
                        R.id.editYear4 -> {
                            if (charSequence.isNotEmpty())
                                submitButton.requestFocus()
                            else
                                editYear3.requestFocus()
                        }
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {}
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        })

    }

    override fun save(validate: Boolean) {
        if (validate) {
            dateOfBirth = editDay.text.toString() + editDay2.text.toString() + "." +
                    editMonth.text.toString() + editMonth2.text.toString() + "." +
                    editYear.text.toString() + editYear2.text.toString() +
                    editYear3.text.toString() + editYear4.text.toString()
            EditProfileController.birthDate = dateOfBirth
            finish()
        }
    }

    private fun setStartState() {
        editDay.setText("")
        editDay2.setText("")
        editMonth.setText("")
        editMonth2.setText("")
        editYear.setText("")
        editYear2.setText("")
        editYear3.setText("")
        editYear4.setText("")
    }

    override fun validate(): Boolean {
        if (editDay.text.toString() == "" || editDay2.text.toString() == ""
                || editMonth.text.toString() == "" || editMonth2.text.toString() == ""
                || editYear.text.toString() == "" || editYear2.text.toString() == ""
                || editYear3.text.toString() == "" || editYear4.text.toString() == ""
        ) {
            Toast.makeText(this, R.string.error_date, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
