package com.example.myapplication.controllers.createEvent

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapters.MemberAdapter
import com.example.myapplication.models.EventModel
import com.example.myapplication.page.EditController
import kotlinx.android.synthetic.main.create_event_add_members_controller.*
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.models.EventMember


class CreateEventAddMembersController : EditController() {
    private val CONTACT_PERMISSION = 1

    private val FRIENDS_STATE = 0
    private val CONTACTS_STATE = 1
    private var eventState = FRIENDS_STATE

    private val ALL_CONTACT = 0
    private val MY_EVENT_CONTACT = 1
    private var contactState = ALL_CONTACT

    private lateinit var builder: EventModel.Builder

    private lateinit var friendList: List<EventMember>
    private lateinit var contactList: List<EventMember>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event_add_members_controller)
    }

    override fun init() {
        super.init()
        fetchFriends()
        fetchContactList()
        recycler.adapter = MemberAdapter(friendList)
        recycler.layoutManager = LinearLayoutManager(this)
        builder = innerObject as EventModel.Builder
    }

    override fun setListeners() {
        setContactsListener()
        setFriendsListener()
        setSubmitListener()
        setMyEventContactListener()
        setAllContactListener()
        setSearchListener()
    }

    private fun setSearchListener(){
        //TODO
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
                setFriendList()
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
                setContactList()
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
        submitButton.setOnClickListener {
            save()
        }
    }

    override fun save(validate: Boolean) {
        if (validate){
            val builder = innerObject as EventModel.Builder
            //TODO add membmers
            startEditOrViewActivity(CreateEventChoosePhoto::class.java, builder)
        }
    }

    //TODO CHECK
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

    private fun setFriendList(){
        recycler.adapter = MemberAdapter(friendList)
    }

    private fun setContactList(){
        recycler.adapter = MemberAdapter( contactList)
    }

    private fun fetchFriends(){
        friendList = ArrayList() //TODO
    }

    private fun fetchContactList(){
        if (hasContactPermission()) {
            val rowContacts = mutableListOf<EventMember>()
            val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
            while (phones!!.moveToNext()) {
                val name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumb = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val photoUri = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                rowContacts.add(EventMember(name, phoneNumb, photoUri))

            }
            contactList  = rowContacts.distinctBy { it.phoneNumb }
            phones.close()
            recycler.adapter = MemberAdapter(contactList)

        }else{
            getContactPermission()
        }
    }

    override fun onStart() {
        super.onStart()
        if (CreateEventNavigation.clearBackStack){
            finish()
        }
        if (eventState == FRIENDS_STATE){
            setFriendList()
        }else{
            //TODO разделения на свои и my event
            setContactList()
        }
    }
}
