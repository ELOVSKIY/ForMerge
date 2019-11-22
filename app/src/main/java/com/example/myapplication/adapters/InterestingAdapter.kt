package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick


class InterestingAdapter :
    RecyclerView.Adapter<InterestingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.interesting_adapter, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return 10 //TODO CHANGE ADAPTER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView){
        //TODO
    }

}