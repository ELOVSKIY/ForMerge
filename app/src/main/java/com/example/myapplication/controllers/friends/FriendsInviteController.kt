package com.example.myapplication.controllers.friends

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.MemberAdapter
import com.example.myapplication.models.EventMember
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.create_event_add_members_controller.*

class FriendsInviteController : PageViewController() {
    private val CONTACT_PERMISSION = 1
    private lateinit var friendList: List<EventMember>
    private lateinit var contactList: List<EventMember>

    private val ALL_CONTACT = 0
    private val MY_EVENT_CONTACT = 1
    private var contactState = ALL_CONTACT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friends_add_friend_controller)
    }

    override fun setListeners() {
        setMyEventContactListener()
        setAllContactListener()
    }

    override fun init() {
        fetchContactList()
        fetchFriends()
        recycler.adapter = MemberAdapter(contactList)
        recycler.layoutManager = LinearLayoutManager(this)
    }

    private fun setAllContactListener(){
        allContact.setOnClickListener {
            if (contactState != ALL_CONTACT){
                allContact.setTextColor(Color.WHITE)
                myEventContact.setTextColor(Color.GRAY)
                contactState = ALL_CONTACT
                setContactList()
            }
        }
    }

    private fun setMyEventContactListener(){
        myEventContact.setOnClickListener {
            if (contactState != MY_EVENT_CONTACT){
                allContact.setTextColor(Color.GRAY)
                myEventContact.setTextColor(Color.WHITE)
                contactState = MY_EVENT_CONTACT
                setFriendList()
            }
        }
    }

    private fun hasContactPermission(): Boolean{
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getContactPermission(){
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                CONTACT_PERMISSION
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CONTACT_PERMISSION -> {
                if (resultCode == Activity.RESULT_OK){
                    fetchContactList()
                }
            }
        }
    }

    private fun setFriendList(){
        recycler.adapter = MemberAdapter(friendList)
    }

    private fun setContactList(){
        recycler.adapter = MemberAdapter(contactList)
    }

    private fun fetchContactList(){
        if (hasContactPermission()) {
            val rawContacts = mutableListOf<EventMember>()
            val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
            while (phones!!.moveToNext()) {
                val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumb = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val photoUri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                rawContacts.add(EventMember(name, phoneNumb, photoUri))

            }
            contactList = rawContacts.distinctBy { it.phoneNumb }
            phones.close()
            recycler.adapter = MemberAdapter(contactList)

        }else{
            getContactPermission()
        }
    }

    private fun fetchFriends(){
        friendList = ArrayList() //TODO
    }

    override fun onStart() {
        super.onStart()
        setContactList()
    }
}
