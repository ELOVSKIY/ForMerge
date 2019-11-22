package com.example.myapplication.controllers.profile

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController.Companion.dataObject
import com.example.myapplication.page.PageViewController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_main_screen_controller.*

class ProfileMainScreenController :  PageViewController(){

    private lateinit var profile: User



    private lateinit var editButton: Button
    private lateinit var profileImage: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var textViewBirthday: TextView
    private lateinit var textViewGender: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_main_screen_controller)
    }

    override fun init() {
        profile = dataObject as User
        profileImage = findViewById(R.id.imageUser)
        textViewName = findViewById(R.id.textViewName)
        textViewPhone = findViewById(R.id.textViewPhone)
        textViewBirthday = findViewById(R.id.textViewDateOfBirth)
        textViewGender = findViewById(R.id.textViewGender)
        editButton = findViewById(R.id.editButton)

        textViewName.text = profile.Name
        textViewPhone.text = profile.Phone
        textViewBirthday.text = profile.Date_birthday
        textViewGender.text = profile.Gender

        Picasso.get()
            .load(profile.getUserPhotoUri())
            .fit()
            .centerCrop()
            .into(profileImage)
        setListeners()


    }

    override fun setListeners(){
        setOnEditListener()
        setOnBackListener()

    }

    private fun setOnBackListener(){
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setOnEditListener(){
        editButton.setOnClickListener {
            startEditOrViewActivity(EditProfileController::class.java, profile)
        }
    }
}
