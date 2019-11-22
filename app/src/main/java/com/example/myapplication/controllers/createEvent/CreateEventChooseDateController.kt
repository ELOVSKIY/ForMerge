package com.example.myapplication.controllers.createEvent

import android.os.Bundle
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.create_event_choose_date.*
import kotlinx.android.synthetic.main.create_event_name_controller.submitButton
import com.example.myapplication.R
import com.example.myapplication.models.EventModel
import java.lang.StringBuilder
import java.util.*


class CreateEventChooseDateController : EditController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_choose_date)
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack){
            finish()
        }
    }

    override fun init() {
        super.init()
        showToolbar()
        setTitle(resources.getString(R.string.events))
        timePicker.setIs24HourView(true)
    }

    override fun setListeners() {
        submitButton.setOnClickListener {
            save()
        }
    }

    override fun save(validate: Boolean) {
        if (validate) {
            val eventBuilder = innerObject as EventModel.Builder
            val date = Date(calendar.date)
            val builder = StringBuilder()
            builder.append(date.day)
            builder.append(".")
            builder.append(date.month)
            builder.append(".")
            builder.append(date.year + 1900)
            builder.append(" - ")
            builder.append(" ")
            builder.append(timePicker.currentHour)
            builder.append(":")
            builder.append(timePicker.currentMinute)
            eventBuilder.date(builder.toString())
            startEditOrViewActivity(CreateEventChoosePlaceController::class.java, eventBuilder)
        }
    }



}
