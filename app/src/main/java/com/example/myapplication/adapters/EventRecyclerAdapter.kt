package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick


class EventRecyclerAdapter :
    RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    private var listener: OnRecyclerItemClick? = null

    fun setOnClickListener(listener: OnRecyclerItemClick){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_recycler_card, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return 10 //TODO CHANGE ADAPTER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardView.setOnClickListener {
            listener?.onClick(position)
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView){
        val image: ImageView = cardView.findViewById(R.id.imageViewEvent)
        val dateText: TextView = cardView.findViewById(R.id.textViewDate)
        val placeText: TextView = cardView.findViewById(R.id.textViewPlace)
        val eventText: TextView = cardView.findViewById(R.id.textViewEvent)
    }

}