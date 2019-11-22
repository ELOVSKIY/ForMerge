package com.example.myapplication.controllers.myEventMembers

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.MemberAdapter
import com.example.myapplication.controllers.createEvent.CreateEventNavigation
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.create_event_add_members_controller.*

class MyEventAddMembers : PageViewController() {
    private val FRIENDS_STATE = 0
    private val CONTACTS_STATE = 1
    private var eventState = FRIENDS_STATE

    private val ALL_CONTACT = 0
    private val MY_EVENT_CONTACT = 1
    private var contactState = ALL_CONTACT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_add_members_controller)
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack){
            finish()
        }
    }

    override fun init() {
        super.init()
        recycler.adapter = MemberAdapter(ArrayList()) //TODO WTF
        recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun setListeners() {
        setContactsListener()
        setFriendsListener()
        setSubmitListener()
        setMyEventContactListener()
        setAllContactListener()
    }

    private fun setAllContactListener(){
        allContact.setOnClickListener {
            if (contactState != ALL_CONTACT){
                allContact.setTextColor(Color.WHITE)
                myEventContact.setTextColor(Color.GRAY)
                contactState = ALL_CONTACT
            }
        }
    }

    private fun setMyEventContactListener(){
        myEventContact.setOnClickListener {
            if (contactState != MY_EVENT_CONTACT){
                allContact.setTextColor(Color.GRAY)
                myEventContact.setTextColor(Color.WHITE)
                contactState = MY_EVENT_CONTACT
            }
        }
    }


    private fun setFriendsListener() {
        myFriensButton.setOnClickListener {
            if (eventState != FRIENDS_STATE) {
                myFriensButton.background =
                    resources.getDrawable(R.drawable.controlled_shape_left_active)
                myFriensButton.setTextColor(resources.getColor(R.color.colorBlack))

                myContactsButton.background =
                    resources.getDrawable(R.drawable.controlled_shape_right)
                myContactsButton.setTextColor(resources.getColor(R.color.colorWhite))
                eventState = FRIENDS_STATE
                setButtons()
            }
        }
    }

    private fun setContactsListener() {
        myContactsButton.setOnClickListener {
            if (eventState != CONTACTS_STATE) {
                myContactsButton.background =
                    resources.getDrawable(R.drawable.controlled_shape_right_active)
                myContactsButton.setTextColor(resources.getColor(R.color.colorBlack))

                myFriensButton.background =
                    resources.getDrawable(R.drawable.controlled_shape_left)
                myFriensButton.setTextColor(resources.getColor(R.color.colorWhite))
                eventState = CONTACTS_STATE
                setButtons()
            }
        }
    }

    private fun setButtons() {
        if (eventState == FRIENDS_STATE) {
            allContact.visibility = View.GONE
            myEventContact.visibility = View.GONE
        } else {
            allContact.visibility = View.VISIBLE
            myEventContact.visibility = View.VISIBLE
        }
    }

    private fun setSubmitListener(){

    }

}
