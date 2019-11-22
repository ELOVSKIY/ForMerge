package com.example.myapplication.controllers.friends

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.FriendAdapter
import com.example.myapplication.models.EventMember
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.friends_main_screen_controller.*

class FriendsMainScreenController : PageViewController() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_main_screen_controller)
    }

    override fun init() {
        super.init()
        showToolbar()
        setTitle(resources.getString(R.string.my_friends))
        val list = arrayListOf(EventMember("Стасян", "+3746574", null),
                EventMember("Стасян", "+3746574", null), EventMember("Стасян", "+3746574", null))
        recycler.adapter = FriendAdapter(list)
        recycler.layoutManager =LinearLayoutManager(this)
    }

    override fun setListeners() {
        setFABListener()
    }

    private fun setFABListener(){
        floatingButton.setOnClickListener{
            val intent = Intent(this, FriendsInviteController::class.java)
            startActivity(intent)
        }
    }
}
