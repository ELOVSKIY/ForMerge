package com.example.myapplication.controllers.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myapplication.R
import com.example.myapplication.page.PageViewController
import java.util.*


class UserEditAuthMainController : PageViewController() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun edit_language(view: View){
        val dm = resources.displayMetrics
        val conf = resources.configuration

        if (textView_language.text.toString() == "RU"){
            conf.locale = Locale("en")
            resources.updateConfiguration(conf, dm)
        }
        else{
            conf.locale = Locale("ru")
            resources.updateConfiguration(conf, dm)
        }
        //refresh_view
        textView_language.text = resources.getString(R.string.language)
        button.text = resources.getString(R.string.sign_in)
        newUser.text = resources.getString(R.string.newUser)
        sign_up.text = resources.getString(R.string.sign_up)
    }

    fun sign_up(view: View){
        startActivity(Intent (this, UserEditAuthPhoneController::class.java))
    }
}
