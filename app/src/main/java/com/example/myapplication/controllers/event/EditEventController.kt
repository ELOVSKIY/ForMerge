package com.example.myapplication.controllers.event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.page.PageViewController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.edit_event_controller.*

class EditEventController : PageViewController() {
    private val ACCEPT_BUTTON_ID = 1
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_event_controller)
    }

    override fun init() {
        super.init()
        showToolbar()
        setTitle(resources.getString(R.string.edit_event))
        toolbarButtonList.clear()
        addToolbarButton(ACCEPT_BUTTON_ID, "Accept",
            resources.getDrawable(R.drawable.accept_button),
            MenuItem.OnMenuItemClickListener {
                onAcceptClick()
            })
        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationIcon(R.drawable.denied_button)
        setListeners()
    }

    override fun setListeners() {
        setOnChangePhotoListener()
        setOnDeleteEventListener()
    }

    private fun setOnChangePhotoListener() {
        textChangePhoto.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }
    }

    private fun setOnDeleteEventListener() {
        textDeleteEvent.setOnClickListener {
            val alert = AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.want_to_delete))
                .setMessage(resources.getString(R.string.want_to_delete))
                .setPositiveButton(
                    resources.getString(R.string.delete)
                ) { dialog, which -> }
                .setNegativeButton(
                    resources.getString(R.string.cancel)
                ) { dialog, which -> }
                .setCancelable(true)
            alert.show()
        }
    }

    private fun onAcceptClick(): Boolean {
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data
                Picasso.get()
                    .load(fileUri)
                    .into(imageViewEvent)
            }
        }
    }
}