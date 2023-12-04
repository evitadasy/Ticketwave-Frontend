package com.example.bookify_frontend.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.model.Event
import com.example.bookify_frontend.model.EventsResponse
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.database.MatrixCursor
import android.provider.BaseColumns
import com.example.bookify_frontend.model.SuggestionsAdapter
import com.example.bookify_frontend.ui.category.CategoryActivity


lateinit var basetext: TextView
lateinit var btn: Button
private val searchSuggestions = arrayListOf<String>()

const val URL = "http://192.168.1.83:3000/"

class HomeActivity : AppCompatActivity() {

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

        val adapter = ButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ButtonAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                navigateToCategory(position)
            }
        })

        val searchView: SearchView = findViewById(R.id.searchView)
        val searchHints = arrayOf(
            "Hungry?",
            "What do you wanna listen today?",
            "What movie do you wanna watch?",
            "What about a theatre play?"
        )
        val randomIndex = (0 until searchHints.size).random()
        searchView.queryHint = searchHints[randomIndex]

        initSearchSuggestions()

        val suggestionAdapter = SuggestionsAdapter(
            this,
            createCursor(searchSuggestions),
            false
        )
        searchView.suggestionsAdapter = suggestionAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredSuggestions = searchSuggestions.filter {
                    it.startsWith(newText.orEmpty(), ignoreCase = true)
                }

                val cursor = createCursor(filteredSuggestions)
                suggestionAdapter.changeCursor(cursor)

                return true
            }
        })
    }

    private fun createCursor(suggestions: List<String>): MatrixCursor {
        val cursor = MatrixCursor(arrayOf(BaseColumns._ID, "suggestion"))
        for ((index, suggestion) in suggestions.withIndex()) {
            cursor.addRow(arrayOf(index, suggestion))
        }
        return cursor
    }

    private fun initSearchSuggestions() {
        searchSuggestions.add("Thessaloniki")
        searchSuggestions.add("Larissa")
        searchSuggestions.add("Bolos")
        searchSuggestions.add("Athens")
    }

    private fun navigateToCategory(position: Int) {
        val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
        intent.putExtra("CATEGORY_POSITION", position)
        startActivity(intent)
    }

    private fun getData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()

        val retrofitAPI = retrofit.create(ApiService::class.java).getEvents()

        retrofitAPI.enqueue(object : Callback<EventsResponse?> {
            override fun onResponse(
                call: Call<EventsResponse?>,
                response: Response<EventsResponse?>
            ) {
                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val eventsList = eventsResponse.events
                    displayEvents(eventsList)
                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        "Response body is null",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<EventsResponse?>, t: Throwable) {
                val errorMessage = t.message
                Toast.makeText(
                    this@HomeActivity,
                    "Failed: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun displayEvents(eventsList: List<Event>) {
        val displayText = StringBuilder()

        for (event in eventsList) {
            displayText.append("Event Title: ${event.title}\n")
            displayText.append("Event Date: ${event.date}\n")
            displayText.append("Description: ${event.description}\n")

            displayText.append("\n\n")
        }
    }
}