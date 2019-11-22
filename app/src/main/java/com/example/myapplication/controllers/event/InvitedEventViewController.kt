package com.example.myapplication.controllers.event

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.controllers.invitedEventMembers.InvitedEventMembers
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.invited_event_view_controller.*

class InvitedEventViewController : PageViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invited_event_view_controller)
    }

    override fun setListeners() {
        setOnAcceptListener()
        setOnDeniedListener()
        setOnMembersListener()
        setOnBackListener()
    }

    private fun setOnBackListener(){
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setOnMembersListener() {
        memberLayout.setOnClickListener {
            val intent = Intent(this, InvitedEventMembers::class.java)
            startActivity(intent)
        }
    }

    private fun hideButtons() {
        acceptButton.visibility = View.GONE
        deniedButton.visibility = View.GONE
    }

    private fun setEnabledButtons() {
        textMembers.isEnabled = true
        textGifts.isEnabled = true
        textChat.isEnabled = true
    }

    private fun setOnAcceptListener() {
        acceptButton.setOnClickListener {
            hideButtons()
            setEnabledButtons()
        }
    }

    private fun setOnDeniedListener() {
        deniedButton.setOnClickListener {
            hideButtons()
        }
    }
}
