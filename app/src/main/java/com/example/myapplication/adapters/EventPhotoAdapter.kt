package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class EventPhotoAdapter() :
    RecyclerView.Adapter<EventPhotoAdapter.ViewHolder>() {

    private val LOG_TAG: String = "UserEditAuthChoosePhotoFromGalleryAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_recycler_card, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return 10 //TODO CHANGE ADAPTER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView){
        val image: ImageView = cardView.findViewById(R.id.imageViewEvent)
        val dateText: TextView = cardView.findViewById(R.id.textViewDate)
        val placeText: TextView = cardView.findViewById(R.id.textViewPlace)
        val eventText: TextView = cardView.findViewById(R.id.textViewEvent)
    }

}