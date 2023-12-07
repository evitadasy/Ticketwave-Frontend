package com.example.testing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R
import com.example.testing.Event
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class CategoryAdapter(private val itemList: List<Event>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = itemList[position]

        var eventImage = if(currentItem.img?.contains("base64") == true) "https://www.fosonline.gr/media/news/2019/02/24/42431/main/live-paok-aris.jpg" else currentItem.img
        // Load image using Picasso
        Picasso.get()
            .load(eventImage)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(holder.categoryImageView)

        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.city
        holder.datess.text = currentItem.date

        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImageView: ImageView = itemView.findViewById(R.id.categoryImageView)
        val datess: TextView = itemView.findViewById(R.id.datess)
        val titleTextView: TextView = itemView.findViewById(R.id.categoryTitleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.categoryDescriptionTextView)
    }
}