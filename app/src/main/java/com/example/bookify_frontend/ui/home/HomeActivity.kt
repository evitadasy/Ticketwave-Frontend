package com.example.bookify_frontend.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookify_frontend.R
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.api_service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.database.MatrixCursor
import android.provider.BaseColumns
import android.widget.Toast
import com.example.bookify_frontend.model.City
import com.example.bookify_frontend.ui.home.SuggestionsAdapter
import com.example.bookify_frontend.ui.category.CategoryActivity




private val searchSuggestions = arrayListOf<String>()
//private var cities: List<City> = TODO()

class HomeActivity : AppCompatActivity() {

    private val buttonItemList = listOf(
        ButtonItem(R.drawable.cinema, "Cinema"),
        ButtonItem(R.drawable.concert, "Live Shows"),
        ButtonItem(R.drawable.food, "Food"),
        ButtonItem(R.drawable.sports, "Sports"),
        ButtonItem(R.drawable.seminar, "Seminar"),
        ButtonItem(R.drawable.theatre, "Theatre"),
        ButtonItem(R.drawable.kids, "Kids"),
        ButtonItem(R.drawable.museums, "Museums")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchCities()
        setContentView(R.layout.activity_home)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val headerImageView: ImageView = findViewById(R.id.headerImageView)
        headerImageView.setImageResource(R.drawable.logo_small)

        // Initialize το adapter with click listener
        val adapter = ButtonAdapter(buttonItemList)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ButtonAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                navigateToCategory(position)


            }
        })
        // Set up the SearchView
        val searchView: SearchView = findViewById(R.id.searchView)
        val searchHints = arrayOf(
            "Search by city...",
            "Search here..."
        )
        val randomIndex = (0 until searchHints.size).random()
        searchView.queryHint = searchHints[randomIndex]


        //initSearchSuggestions()

        // Create and set the custom adapter for the SearchView
        val suggestionAdapter = SuggestionsAdapter(
            this,
            createCursor(searchSuggestions),
            false
        )
        searchView.suggestionsAdapter = suggestionAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter suggestions based on the entered text
                val filteredSuggestions = searchSuggestions.filter {
                    it.startsWith(newText.orEmpty(), ignoreCase = true)
                }

                // Update the cursor with the filtered suggestions
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

    private fun initSearchSuggestions(cities: List<City>?) {
        searchSuggestions.clear()
        cities?.forEach { city ->
            searchSuggestions.add(city.name)
        }
    }


    private fun navigateToCategory(position: Int) {
        val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
        intent.putExtra("CATEGORY_POSITION", position)
        startActivity(intent)
    }


    private fun fetchCities() {
        val apiService = RetrofitService.createApiService()

        val citiesCall = apiService.getCities()

        citiesCall.enqueue(object : Callback<List<City>?> {
            override fun onResponse(
                call: Call<List<City>?>,
                response: Response<List<City>?>
            ) {
                // Handle cities
                val citiesList = response.body()
                if (citiesList != null) {
                    // Add cities to a List<City>
                    // cities = citiesList // You can uncomment this if you want to store the cities for later use
                    initSearchSuggestions(citiesList)
                } else {
                    Toast.makeText(this@HomeActivity, "Response body is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<City>?>, t: Throwable) {
                val errorMessage = t.message
                Toast.makeText(this@HomeActivity, "Cities Failed: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })

    }

}