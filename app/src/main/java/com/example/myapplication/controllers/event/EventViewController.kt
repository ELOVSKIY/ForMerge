package com.example.myapplication.controllers.event

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.EventPhotoAdapter
import com.example.myapplication.controllers.myEventMembers.MyEventMembersList
import com.example.myapplication.controllers.presents.myPresents.MyPresentsListController
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.event_view_controller.*

class EventViewController : PageViewController() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_view_controller)
    }

    override fun init() {
        super.init()
        recycler.adapter = EventPhotoAdapter()
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun setListeners() {
        setOnBackListener()
        setOnMemberListener()
        setOnGiftListener()
        setOnChatListener()
    }

    private fun setOnBackListener(){
        backButton.setOnClickListener {
           finish()
        }
    }

    private fun setOnMemberListener(){
        memberLayout.setOnClickListener {
            val intent = Intent(this, MyEventMembersList::class.java)
            startActivity(intent)

        }
    }

    private fun setOnGiftListener(){
        giftLayout.setOnClickListener {
            val intent = Intent(this, MyPresentsListController::class.java)
            startActivity(intent)
        }
    }

    private fun setOnChatListener(){
        chatLayout.setOnClickListener {

        }
    }
}
