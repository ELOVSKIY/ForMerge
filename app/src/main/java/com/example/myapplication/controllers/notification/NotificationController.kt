package com.example.myapplication.controllers.notification

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.NotificationAdapter
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.activity_notification_controller.*

class NotificationController : PageViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_controller)
    }

    override fun init() {
        showToolbar()
        setTitle(resources.getString(R.string.notifications))
        recycler.adapter = NotificationAdapter()
        recycler.layoutManager = LinearLayoutManager(this)
    }


}
