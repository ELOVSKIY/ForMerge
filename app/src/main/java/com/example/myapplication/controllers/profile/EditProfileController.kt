package com.example.myapplication.controllers.profile

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.models.User
import com.example.myapplication.page.EditController
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class EditProfileController : EditController() {
    private val ACCEPT_BUTTON_ID = 1
    private val DENIED_BUTTON_ID = 2
    private val CHANGE_PHOTO_REQUEST = 3

    private lateinit var toolbar: Toolbar

    private lateinit var image: CircleImageView
    private lateinit var changePhoto: TextView
    private lateinit var editPhoneNumb: EditText
    private lateinit var editName: EditText
    private lateinit var editBirthDate: EditText
    private lateinit var maleButton: RadioButton
    private lateinit var femaleButton: RadioButton

    private lateinit var profile: User


    companion object {
        var isChanged = false
        var photoUri: Uri? = null
        var circlePhotoUri: Uri? = null
        var birthDate: String? = null
        private lateinit var name: String
        private lateinit var gender: String
        private lateinit var phoneNumb: String

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit_controller)
    }

    override fun init() {
        super.init()

        showToolbar()
        setTitle(resources.getString(R.string.edit_profile_title))
        toolbarButtonList.clear()
        addToolbarButton(ACCEPT_BUTTON_ID, "Accept",
            resources.getDrawable(R.drawable.accept_button),
            MenuItem.OnMenuItemClickListener {
                acceptChanges()
            })
        toolbar = findViewById(R.id.toolbar)
        toolbar!!.setNavigationIcon(R.drawable.denied_button)
        profile = dataObject as User
        editPhoneNumb = findViewById(R.id.editTextPhone)
        editName = findViewById(R.id.editTextName)
        editBirthDate = findViewById(R.id.editTextBirthDate)
        maleButton = findViewById(R.id.radioButtonMale)
        femaleButton = findViewById(R.id.radioButtonFemale)
        changePhoto = findViewById(R.id.textViewChangeImage)
        image = findViewById(R.id.profileImage)

        if (!isChanged) {
            birthDate = profile.getUserDateOfBirth()
            name = profile.Name
            gender = profile.Gender
            phoneNumb = profile.Phone
            photoUri = profile.getUserPhotoUri()
            circlePhotoUri = profile.getUserPhotoCircleUri()
        }
        setView()
        isChanged = false
    }

    private fun saveState() {
        name = editName.text.toString()
        phoneNumb = editPhoneNumb.text.toString()
        gender = if (maleButton.isChecked) resources.getString(R.string.male)
            else resources.getString(R.string.female)

    }

    private fun setView() {
        editPhoneNumb.setText(phoneNumb)
        editName.setText(name)
        editBirthDate.setText(birthDate)
        Picasso.get()
            .load(circlePhotoUri)
            .into(image)
        if (gender == resources.getString(R.string.male)) {
            maleButton.isChecked = true
        } else {
            femaleButton.isChecked = true
        }
    }

    private fun setOnChangePhotoListener() {
        changePhoto.setOnClickListener {
            isChanged = true
            saveState()
            startEditOrViewActivity(ChangeProfilePhoto::class.java, profile)
            setView()
        }
    }

    override fun setListeners() {
        setOnChangePhotoListener()
        setPhoneListener()
        setBirthDateListener()
    }

    private fun setBirthDateListener(){
        editBirthDate.setOnClickListener {
            isChanged = true
            saveState()
            startEditOrViewActivity(ProfileChangeDate::class.java, profile)
            setView()
        }
    }

    private fun acceptChanges(): Boolean {
        dataObject = User().createUserBuilder(profile)
            .setUser_PhotoUri(photoUri)
            .setUser_PhotoCircleUri(circlePhotoUri)
            .setUser_DateOfBirth(editBirthDate.text.toString())
            .setUser_Name(editName.text.toString())
            .setUser_Phone(editPhoneNumb.text.toString())
            .setUser_Gender(genderToString())
            .build()
        finish()
        return true
    }

    private fun setPhoneListener(){
        editPhoneNumb.addTextChangedListener(object : TextWatcher {
            lateinit var buffer: CharSequence
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length == 2) {
                    buffer = charSequence
                }
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.length < 2){
                    editPhoneNumb.setText(buffer)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun genderToString(): String{
        return if (maleButton.isChecked){
            resources.getString(R.string.male)
        }else{
            resources.getString(R.string.female)
        }
    }
}