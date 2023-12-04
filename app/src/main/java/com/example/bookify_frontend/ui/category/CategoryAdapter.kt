package com.example.bookify_frontend.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookify_frontend.R

class CategoryAdapter(private val itemList: List<CategoryActivity.CategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = itemList[position]

        // Load image using Glide or any other image loading library
        Glide.with(holder.itemView)
            .load(currentItem.imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(holder.categoryImageView)

        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImageView: ImageView = itemView.findViewById(R.id.categoryImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.categoryTitleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.categoryDescriptionTextView)
    }
}