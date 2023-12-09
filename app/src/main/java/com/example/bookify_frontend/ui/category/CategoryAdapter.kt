package com.example.testing

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


class CategoryAdapter(private var itemList: List<Event>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }
    fun shuffleData() {
        itemList = itemList.toMutableList().apply { shuffle() }
        notifyDataSetChanged()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = itemList[position]

        var eventImage = if(currentItem.img?.contains("base64") == true) "https://www.fosonline.gr/media/news/2019/02/24/42431/main/live-paok-aris.jpg" else currentItem.img
        // Load image using Picasso
        Picasso.get()
            .load(eventImage)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(holder.categoryImageView)

        //Format Date
        // Parse the input date string
        val instant = Instant.parse(currentItem.date)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

        // Format for the hourText (e.g., 5pm/3am)
        val hourFormatter = DateTimeFormatter.ofPattern("ha", Locale.UK)
        val hourText = localDateTime.format(hourFormatter)

        // Format for the dayText (e.g., 12 Mar 2023)
        val dayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.UK)
        val dayText = localDateTime.format(dayFormatter)

        holder.titleTextView.text = currentItem.title
        holder.place.text = currentItem.location
        holder.day.text = dayText
        holder.hour.text = hourText



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

        val titleTextView: TextView = itemView.findViewById(R.id.categoryTitle)
        val day: TextView = itemView.findViewById(R.id.day)
        val place: TextView = itemView.findViewById(R.id.city)
        val hour: TextView = itemView.findViewById(R.id.hour)

    }
}