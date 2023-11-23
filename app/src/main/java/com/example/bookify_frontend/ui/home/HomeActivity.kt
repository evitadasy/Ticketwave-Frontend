package com.example.bookify_frontend.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.SearchView
import com.example.bookify_frontend.R


class HomeActivity : ComponentActivity() {

    private lateinit var headerImageView: ImageView
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)


        val headerImageView: ImageView = findViewById(R.id.headerImageView)
        headerImageView.setImageResource(R.drawable.header)




// this is the list of buttons and their texts!
        val buttonItemList = listOf(
            ButtonItem(R.drawable.cinema, "Cinema"),
            ButtonItem(R.drawable.concert, "Concert"),
            ButtonItem(R.drawable.food, "Food"),
            ButtonItem(R.drawable.sports, "Sports"),
            ButtonItem(R.drawable.seminar, "Seminar"),
            ButtonItem(R.drawable.theatre, "Theatre"),
            ButtonItem(R.drawable.kids, "Kids"),
            ButtonItem(R.drawable.museums, "Museums")

        )
        val adapter = ButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter




//random search view text from here

        val searchView: SearchView = findViewById(R.id.searchView)
        val searchHints = arrayOf(
            "Hungry?",
            "What do you wanna listen today ?",
            "What movie do you wanna watch?",
            "What about a theatre play?"
        )
        val randomIndex = (0 until searchHints.size).random()
        searchView.queryHint = searchHints[randomIndex]


    }
}
