package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.EventMember
import com.squareup.picasso.Picasso


class FriendAdapter(
        var memberList: List<EventMember>
) :
        RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_card, parent, false) as ConstraintLayout
        return ViewHolder(layout)
    }


    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.contactName.text = memberList[pos].name
        memberList[pos].contactPhoto?.let{
            Picasso.get()
                    .load(memberList[pos].contactPhoto)
                    .into(holder.contactImage)
        }
    }

    class ViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        val contactName: TextView = layout.findViewById(R.id.textContactName)
        val contactImage: ImageView = layout.findViewById(R.id.imageContact)
    }

}