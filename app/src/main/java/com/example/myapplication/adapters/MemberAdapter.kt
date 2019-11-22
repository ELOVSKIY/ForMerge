package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.EventMember
import com.squareup.picasso.Picasso


class MemberAdapter(
        var memberList: List<EventMember>
) :
        RecyclerView.Adapter<MemberAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_card, parent, false) as ConstraintLayout
        return ViewHolder(layout)
    }


    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.button.isChecked = memberList[pos].invited
        holder.contactName.text = memberList[pos].name
        Picasso.get()
                .load(memberList[pos].contactPhoto)
                .into(holder.contactImage)
        holder.button.setOnClickListener {
            memberList[pos].changeState()
        }
        holder.inviteText.setOnClickListener {

        }
    }

    class ViewHolder(layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        val contactName: TextView = layout.findViewById(R.id.textContactName)
        val contactImage: ImageView = layout.findViewById(R.id.imageContact)
        val button: CheckBox = layout.findViewById(R.id.checkButton)
        val inviteText: TextView = layout.findViewById(R.id.textInvite)
    }

}