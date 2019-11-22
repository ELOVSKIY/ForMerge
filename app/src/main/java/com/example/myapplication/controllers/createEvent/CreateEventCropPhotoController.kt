package com.example.myapplication.controllers.createEvent

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig
import kotlinx.android.synthetic.main.create_event_crop_photo_controller.*

class CreateEventCropPhotoController : EditController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R
                .layout.create_event_crop_photo_controller)
    }

       override fun init() {
        val builder = innerObject as EventModel.Builder
        val uri = builder.imageUri
        imageCrop.setImageUri(uri)
    }

    override fun save(validate: Boolean) {
        if (validate) {
            val builder = innerObject as EventModel.Builder
            val uri = builder.imageUri
            imageCrop.crop(CropIwaSaveConfig(uri))
            startEditOrViewActivity(CreateEventFinalStepController::class.java, builder)
        }
    }

    override fun setListeners() {
        setSubmitButtonListener()
    }

    private fun setSubmitButtonListener() {
        submitButton.setOnClickListener {
            save()
        }
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack) {
            finish()
        }
    }
}
