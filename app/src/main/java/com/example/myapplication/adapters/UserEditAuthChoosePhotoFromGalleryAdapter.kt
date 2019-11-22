package com.example.myapplication.adapters

import android.view.ViewGroup
import com.example.myapplication.R
import android.net.Uri
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CallBackInterface.OnRecyclerItemClick
import com.squareup.picasso.Picasso


class UserEditAuthChoosePhotoFromGalleryAdapter(
                                                private val imagesList: List<Uri>) :
    RecyclerView.Adapter<UserEditAuthChoosePhotoFromGalleryAdapter.ViewHolder>() {

    private var listener: OnRecyclerItemClick? = null


    fun setOnItemClickListener(listener: OnRecyclerItemClick){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val image = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_for_selection_from_gallery, parent, false)
                as CardView
        return ViewHolder(image)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(imagesList[position])
            .fit()
            .placeholder(R.drawable.ic_cloud_download_black_24dp)
            .error(R.drawable.ic_error_black_24dp)
            .into(holder.image)
        holder.image.setOnClickListener {
            listener?.onClick(position)
        }
    }

    class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView){
        val image: ImageView = cardView.findViewById(R.id.cardImage)
    }
}