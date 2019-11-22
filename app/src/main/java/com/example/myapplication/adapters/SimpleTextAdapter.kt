package com.example.myapplication.adapters

import android.content.Context
import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class SimpleTextAdapter(
    context: Context,
    val listener: OnChooseEventListener
) :
    RecyclerView.Adapter<SimpleTextAdapter.ViewHolder>() {

    private val LOG_TAG: String = "UserEditAuthChoosePhotoFromGalleryAdapter"

    private val eventList = context.resources.getStringArray(R.array.event_names)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_text_card, parent, false) as RelativeLayout
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = eventList[position]
        holder.name.setOnClickListener {
            listener.onChoose(holder.name.text.toString())
        }
    }

    class ViewHolder(layout: RelativeLayout) : RecyclerView.ViewHolder(layout) {
        val name: TextView = layout.findViewById(R.id.text)
    }

    interface OnChooseEventListener {
        fun onChoose(eventName: String)
    }
}