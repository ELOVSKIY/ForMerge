package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SelectableArrayAdapter(
    private val textArray: Array<String>,
    private val listener: OnChooseEventListener
) :
    RecyclerView.Adapter<SelectableArrayAdapter.ViewHolder>() {
    private var selectedPosition: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_text_card, parent, false) as RelativeLayout
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return textArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = textArray[position]
        holder.name.setOnClickListener {
            listener.onChoose(holder.name.text.toString())
            selectedPosition = position
            notifyDataSetChanged()
        }

        if (position == selectedPosition){
            holder.layout.background = holder.layout.context.resources.getDrawable(R.drawable.recycler_yellow_element_20dp)
        }else{
            holder.layout.background = holder.layout.context.resources.getDrawable(R.drawable.recycler_white_element_20dp)
        }
    }

    class ViewHolder(val layout: RelativeLayout) : RecyclerView.ViewHolder(layout) {
        val name: TextView = layout.findViewById(R.id.textEventName)
    }

    interface OnChooseEventListener {
        fun onChoose(eventName: String)
    }

}