package com.example.myapplication.controllers.presents.myPresents

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapters.InterestingAdapter
import com.example.myapplication.adapters.WantItemAdapter
import com.example.myapplication.animation.initView
import com.example.myapplication.animation.rotateFab
import com.example.myapplication.animation.showIn
import com.example.myapplication.animation.showOut
import com.example.myapplication.page.PageViewController
import kotlinx.android.synthetic.main.my_presents_list_controller.*
import kotlinx.android.synthetic.main.want_list_controller.actionButton
import kotlinx.android.synthetic.main.want_list_controller.burgerButton
import kotlinx.android.synthetic.main.want_list_controller.interestingRecycler
import kotlinx.android.synthetic.main.want_list_controller.likeRecycler
import kotlinx.android.synthetic.main.want_list_controller.photoButton
import kotlinx.android.synthetic.main.want_list_controller.wantRecycler

class MyPresentsListController : PageViewController() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_presents_list_controller)
    }

    override fun init() {
        super.init()

        showToolbar()
        setTitle(resources.getString(R.string.gift_list))

        interestingRecycler.adapter = InterestingAdapter()
        interestingRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        likeRecycler.adapter = InterestingAdapter()
        likeRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        wantRecycler.adapter = WantItemAdapter(false)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.isAutoMeasureEnabled = true
        wantRecycler.layoutManager = layoutManager
        wantRecycler.isNestedScrollingEnabled = false
    }

    override fun setListeners() {
        setFABListener()
    }

    private fun setFABListener(){
        var openState = false
        initView(likeButton)
        initView(burgerButton)
        initView(photoButton)
        actionButton.setOnClickListener {
            rotateFab(it,!openState)
            openState = !openState
            if (openState){
                showIn(likeButton)
                showIn(burgerButton)
                showIn(photoButton)
            }else{
                showOut(likeButton)
                showOut(burgerButton)
                showOut(photoButton)
            }
        }
    }
}
