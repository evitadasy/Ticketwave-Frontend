package com.example.bookify_frontend.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R

data class ButtonItem(val imageResourceId: Int, val buttonText: String)

class ButtonAdapter(private val buttonList: List<ButtonItem>) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val buttonItem = buttonList[position]
        holder.imageButton.setImageResource(buttonItem.imageResourceId)
        holder.buttonText.text = buttonItem.buttonText
    }

    override fun getItemCount(): Int {
        return buttonList.size
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton: ImageView = itemView.findViewById(R.id.imageButton)
        val buttonText: TextView = itemView.findViewById(R.id.buttonText)


    }
}
