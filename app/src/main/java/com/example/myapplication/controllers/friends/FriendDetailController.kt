package com.example.myapplication.controllers.friends

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.friend_detail_controller.*

class FriendDetailController : PageViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friend_detail_controller)
    }

    override fun setListeners() {
        setOnBackListener()
    }

    private fun setOnBackListener(){
        backButton.setOnClickListener {
            finish()
        }
    }
}
