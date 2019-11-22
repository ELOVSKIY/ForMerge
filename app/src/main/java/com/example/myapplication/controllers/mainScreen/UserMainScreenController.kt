package com.example.myapplication.controllers.mainScreen

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.controllers.event.EventMainPageController
import com.example.myapplication.controllers.friends.FriendsMainScreenController
import com.example.myapplication.models.User
import com.example.myapplication.controllers.notification.NotificationController
import com.example.myapplication.controllers.profile.ProfileMainScreenController
import com.example.myapplication.controllers.want.WantListController
import com.example.myapplication.page.EditController.Companion.dataObject
import com.example.myapplication.page.PageViewController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_main_screen_controller.*

class UserMainScreenController : PageViewController() {

    private lateinit var profile: User



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main_screen_controller)
        profile = dataObject as User
    }

    override fun init() {
        Picasso.get()
            .load(profile.getUserPhotoCircleUri())
            .placeholder(R.drawable.ic_cloud_download_black_24dp)
            .error(R.drawable.ic_error_black_24dp)
            .into(imageUser )

    }

    override fun setListeners() {
        setProfileButtonListener()
        setEventsListener()
        setInvitedListener()
        setNotificationListener()
        setFriendsListener()
        setWantListener()
    }

    private fun setWantListener(){
        wantLayout.setOnClickListener {
            val intent = Intent(this, WantListController::class.java)
            startActivity(intent)
        }
    }


    private fun setNotificationListener(){
        notificationLayout.setOnClickListener {
            val intent = Intent(this, NotificationController::class.java)
            startActivity(intent)
        }
    }

    private fun setInvitedListener(){
        invitedLayout.setOnClickListener {
            val intent = Intent(this, EventMainPageController::class.java)
            startActivity(intent)
        }
    }

    private fun setEventsListener(){
        invitedLayout.setOnClickListener {
            val intent = Intent(this, EventMainPageController::class.java)
            startActivity(intent)
        }

    }

    private fun setProfileButtonListener(){
        profileButton.setOnClickListener {
            startEditOrViewActivity(ProfileMainScreenController::class.java, profile)
        }
    }

    private fun setFriendsListener(){
        friendsLayout.setOnClickListener{
            val intent = Intent(this, FriendsMainScreenController::class.java)
            startActivity(intent)
        }
    }




}
