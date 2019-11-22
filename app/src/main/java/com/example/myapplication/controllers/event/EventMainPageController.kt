package com.example.myapplication.controllers.event

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick
import com.example.myapplication.adapters.EventRecyclerAdapter
import com.example.myapplication.controllers.createEvent.CreateEventNameController
import com.example.myapplication.page.ViewController
import kotlinx.android.synthetic.main.event_main_page_controller.*

class EventMainPageController : ViewController(),
OnRecyclerItemClick{

    private var state = 0
    private val EVENT_STATE = 0
    private val INVITATION_STATE = 1
    private lateinit var eventButton: Button
    private lateinit var invitationButton: Button

    private lateinit var adapter: EventRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_main_page_controller)
    }

    override fun init() {
        showToolbar()
        setTitle(resources.getString(R.string.events))
        super.init()
        adapter =  EventRecyclerAdapter()
        adapter.setOnClickListener(this)
        eventButton = findViewById(R.id.myFriensButton)
        invitationButton = findViewById(R.id.myContactsButton)
        eventRecycler.adapter = adapter
        eventRecycler.layoutManager = LinearLayoutManager(this)
        setListeners()
    }

    override fun setListeners() {
        setEventListener()
        setInvitationListener()
        setCreateListener()
        setSearchListener()
    }

    private fun setSearchListener() {
        //TODO
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }

    private fun setEventListener() {
        eventButton.setOnClickListener {
            if (state != EVENT_STATE) {
                eventButton.background =
                        resources.getDrawable(R.drawable.controlled_shape_left_active)
                eventButton.setTextColor(resources.getColor(R.color.colorBlack))

                invitationButton.background =
                        resources.getDrawable(R.drawable.controlled_shape_right)
                invitationButton.setTextColor(resources.getColor(R.color.colorWhite))
                state = EVENT_STATE

                createEventButton.show()
            }
        }
    }

    private fun setInvitationListener() {
        invitationButton.setOnClickListener {
            if (state != INVITATION_STATE) {
                invitationButton.background =
                        resources.getDrawable(R.drawable.controlled_shape_right_active)
                invitationButton.setTextColor(resources.getColor(R.color.colorBlack))

                eventButton.background =
                        resources.getDrawable(R.drawable.controlled_shape_left)
                eventButton.setTextColor(resources.getColor(R.color.colorWhite))
                state = INVITATION_STATE

                createEventButton.hide()
            }
        }
    }

    private fun setCreateListener() {
        createEventButton.setOnClickListener {
            val intent = Intent(this, CreateEventNameController::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(pos: Int) {
        //TODO STUB
        if (state == EVENT_STATE){
            val intent = Intent(this, EventViewController::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, InvitedEventViewController::class.java)
            startActivity(intent)
        }

    }
}
