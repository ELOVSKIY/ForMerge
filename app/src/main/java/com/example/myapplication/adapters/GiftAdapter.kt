package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GiftAdapter() :
    RecyclerView.Adapter<GiftAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.gift_recycler_card, parent, false) as RelativeLayout
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return 10 //TODO CHANGE ADAPTER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(layout: RelativeLayout) : RecyclerView.ViewHolder(layout){
        val image: ImageView = layout.findViewById(R.id.image)
        val price: TextView = layout.findViewById(R.id.price)
        val description: TextView = layout.findViewById(R.id.description)
    }

}