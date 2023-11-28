package com.example.bookify_frontend.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookify_frontend.R


class HomeActivity : AppCompatActivity() {

    private lateinit var headerImageView: ImageView
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private val buttonItemList = listOf(
        ButtonItem(R.drawable.cinema, "Cinema"),
        ButtonItem(R.drawable.concert, "Concert"),
        ButtonItem(R.drawable.food, "Food"),
        ButtonItem(R.drawable.sports, "Sports"),
        ButtonItem(R.drawable.seminar, "Seminar"),
        ButtonItem(R.drawable.theatre, "Theatre"),
        ButtonItem(R.drawable.kids, "Kids"),
        ButtonItem(R.drawable.museums, "Museums")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val headerImageView: ImageView = findViewById(R.id.headerImageView)
        headerImageView.setImageResource(R.drawable.logo)

        // Initialize το adapter with click listener
        val adapter = ButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object:ButtonAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@HomeActivity,"Hello$position", Toast.LENGTH_SHORT).show()

                // edw anti gia toast ena val intend
                // paradeigma --->
                // var selectedCategory = ""
                //when(position)  {
                //      0 -> selectedCategory =  "cinema"
                //       1 -> selectedCategory= "theatro"
                //}

            }


        })
        // Set up the SearchView
        val searchView: SearchView = findViewById(R.id.searchView)
        val searchHints = arrayOf(
            "Hungry?",
            "What do you wanna listen today?",
            "What movie do you wanna watch?",
            "What about a theatre play?"
        )
        val randomIndex = (0 until searchHints.size).random()
        searchView.queryHint = searchHints[randomIndex]
    }
}
