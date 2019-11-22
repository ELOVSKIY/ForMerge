package com.example.myapplication.controllers.myEventMembers

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.my_event_members_list.*

class MyEventMembersList : PageViewController(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_event_members_list)
    }

    override fun setListeners() {
        setOnAddMemberListener()
    }

    private fun setOnAddMemberListener(){
        addMember.setOnClickListener{
            val intent = Intent(this, MyEventAddMembers::class.java)
            startActivity(intent)
        }
    }
}
