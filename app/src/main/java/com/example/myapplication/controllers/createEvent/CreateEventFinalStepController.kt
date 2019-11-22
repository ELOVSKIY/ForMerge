package com.example.myapplication.controllers.createEvent

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.create_event_final_step_controller.*

class CreateEventFinalStepController : EditController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_final_step_controller)
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack){
            finish()
        }
    }

    override fun init() {
    }

    override fun setListeners() {
        setSubmitListener()
    }

    private fun setSubmitListener() {
        submitButton.setOnClickListener {
            val eventBuilder = innerObject as EventModel.Builder
            //TODO что-то делать с подарком
            CreateEventNavigation.clearBackStack = true
            finish()
           // startActivity(Intent(this, EventMainPageController::class.java))
        }
    }
}
