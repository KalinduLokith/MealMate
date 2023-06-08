
package com.example.mobilecourseworktwo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ImageDescriptionAdapterDetails (private val imagePair : List<ImageDescriptioPair>) :
    RecyclerView.Adapter<ImageDescriptionAdapterDetails.ViewHolder>() {


    class ViewHolder (photoview:View) : RecyclerView.ViewHolder(photoview)
    {
        val imageView:ImageView =photoview.findViewById(R.id.ViewPhoto)
        val descriptionText: TextView = photoview.findViewById(R.id.descriptionText)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val display = LayoutInflater.from(parent.context).inflate(R.layout.activity_find_meal_by_ingredient,parent,false)
        return ViewHolder(display)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = imagePair[position]
        holder.imageView.setImageBitmap(match.image)
        holder.descriptionText.text = (match.description)
    }

    override fun getItemCount(): Int = imagePair.size


}
