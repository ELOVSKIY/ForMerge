package com.example.myapplication.controllers.createEvent

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.SimpleTextAdapter
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.create_event_name_controller.*

class CreateEventNameController : EditController(), SimpleTextAdapter.OnChooseEventListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_name_controller)
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack){
            CreateEventNavigation.clearBackStack= false
            finish()
        }
    }

    override fun init() {
        super.init()
        showToolbar()
        setTitle(resources.getString(R.string.event_name))
        addTextView(
            resources.getString(R.string.step15),
            resources.getString(R.string.nothing)
        )

        recycler.adapter = SimpleTextAdapter(this, this)
        recycler.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        submitButton.setOnClickListener {
            save()
        }
        innerObject = EventModel.Builder()
    }

    override fun onChoose(eventName: String) {
        editEventName.setText(eventName)
    }

    override fun save(validate: Boolean) {
        if (validate){
            val eventBuilder = innerObject as EventModel.Builder
            eventBuilder.name(editEventName.text.toString())
                .description(editEventDescription.text.toString())
            startEditOrViewActivity(CreateEventChooseDateController::class.java, eventBuilder)
        }
    }

    override fun validate(): Boolean {
        return if (editEventName.text.isBlank() || editEventName.text.isBlank()){
            makeToast(resources.getString(R.string.blank_field))
            false
        }else{
            true
        }
    }
}
