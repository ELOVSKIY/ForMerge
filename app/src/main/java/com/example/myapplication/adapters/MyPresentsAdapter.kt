package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.present_card.view.*


class MyPresentsAdapter(val selectable: Boolean) :
        RecyclerView.Adapter<MyPresentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.present_card, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return 10 //TODO CHANGE ADAPTER
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setListeners(holder.cardView)
        holder.cardView.checkButton.isEnabled = false
    }

    private fun setListeners(cardView: CardView) {
        setCrossListener(cardView)
        setChangeListener(cardView)
    }

    private fun setCrossListener(cardView: CardView) {
        cardView.crossIcon.setOnClickListener {
            cardView.textChange.visibility = View.VISIBLE
            cardView.crossIcon.visibility = View.GONE
            cardView.radioGroup.visibility = View.GONE
            cardView.statusLayout.visibility = View.VISIBLE
        }
    }

    private fun setChangeListener(cardView: CardView) {
        cardView.textChange.setOnClickListener {
            cardView.textChange.visibility = View.GONE
            cardView.crossIcon.visibility = View.VISIBLE
            cardView.radioGroup.visibility = View.VISIBLE
            cardView.statusLayout.visibility = View.GONE
        }
    }

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

}