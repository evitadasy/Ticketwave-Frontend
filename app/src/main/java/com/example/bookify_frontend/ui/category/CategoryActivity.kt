package com.example.bookify_frontend.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.RetrofitService
import com.example.bookify_frontend.ui.booking.BookingActivity
import com.example.testing.CategoryAdapter
import com.example.testing.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    data class CategoryItem(val imageUrl: String, val title: String, val description: String)

    private val categoryItemList = mutableListOf<CategoryItem>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var categoryImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Find the ImageView and SwipeRefreshLayout in your layout
        categoryImageView = findViewById(R.id.categoryImageView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            // Handle the refresh action
            // Clear existing data
            categoryItemList.clear()

            val categoryPosition = intent.getIntExtra("CATEGORY_POSITION", -1)
            val cityPosition = intent.getIntExtra("CITY_POSITION", -1)
            if (categoryPosition != -1) {
                fetchDataForCategory(categoryPosition)
                updateCategoryImage(categoryPosition)
            } else if (cityPosition != -1) {
                fetchDataForCity(cityPosition)
                // Set a default image for cities or hide the ImageView if not needed
                categoryImageView.visibility = View.GONE
            }
        }

        // Get the selected category position and city position from intent
        val categoryPosition = intent.getIntExtra("CATEGORY_POSITION", -1)
        val cityPosition = intent.getIntExtra("CITY_POSITION", -1)

        if (categoryPosition != -1) {
            // Make an API call to fetch data for the selected category
            fetchDataForCategory(categoryPosition)
            updateCategoryImage(categoryPosition)
        } else if (cityPosition != -1) {
            // Make an API call to fetch data for the selected city
            fetchDataForCity(cityPosition)
            // Set a default image for cities or hide the ImageView if not needed
            categoryImageView.visibility = View.GONE
        } else {
            // Handle the case where neither category nor city position is provided
            Toast.makeText(this, "Invalid selection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCategoryImage(categoryPosition: Int) {
        // Update the ImageView based on the selected category
        val categoryDrawableResource = when (categoryPosition) {
            0 -> R.drawable.cinemawave
            1 -> R.drawable.livewave
            2 -> R.drawable.foodwave
            3 -> R.drawable.sportswave
            4 -> R.drawable.seminarwave
            5 -> R.drawable.theatrewave
            6 -> R.drawable.kidswave
            7 -> R.drawable.museumwave
            else -> R.drawable.default_category_image
        }

        categoryImageView.setImageResource(categoryDrawableResource)
    }

    private fun fetchDataForCity(cityPosition: Int) {
        // Indicate the start of the refresh
        swipeRefreshLayout.isRefreshing = true

        val apiService = RetrofitService.createApiService()

        val selectedCity = when (cityPosition) {
            0 -> "Thessaloniki"
            1 -> "Athens"
            2 -> "Patra"
            3 -> "Katerini"
            4 -> "Volos"
            else -> "Heraklion"
        }

        val eventsByCityCall = apiService.getEventsByCity(selectedCity)

        eventsByCityCall.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(
                call: Call<List<Event>?>,
                response: Response<List<Event>?>
            ) {
                // Indicate the end of the refresh
                swipeRefreshLayout.isRefreshing = false

                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@CategoryActivity)
                    categoryAdapter = CategoryAdapter(eventsResponse, this@CategoryActivity)
                    recyclerView.adapter = categoryAdapter

                    // Shuffle the data within the adapter
                    categoryAdapter.shuffleData()
                }
            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                // Handle failure
                // Indicate the end of the refresh
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(
                    this@CategoryActivity,
                    "Failed to fetch events",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun fetchDataForCategory(categoryPosition: Int) {
        // Indicate the start of the refresh
        swipeRefreshLayout.isRefreshing = true

        val apiService = RetrofitService.createApiService()

        val selectedCategory = when (categoryPosition) {
            0 -> "cinema"
            1 -> "live"
            2 -> "food"
            3 -> "sports"
            4 -> "seminar"
            5 -> "theatre"
            6 -> "kids"
            else -> "museum"
        }

        val eventsByTypeCall =
            apiService.getEventsByType(selectedCategory.capitalize(Locale.ROOT))

        eventsByTypeCall.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(
                call: Call<List<Event>?>,
                response: Response<List<Event>?>
            ) {
                // Indicate the end of the refresh
                swipeRefreshLayout.isRefreshing = false

                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@CategoryActivity)
                    categoryAdapter = CategoryAdapter(eventsResponse, this@CategoryActivity)
                    recyclerView.adapter = categoryAdapter

                    // Shuffle the data within the adapter
                    categoryAdapter.shuffleData()
                }
            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                // Handle failure
                // Indicate the end of the refresh
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    // Implement the onItemClick method
    override fun onItemClick(event: Event) {
        // Create an Intent to start the BookingActivity
        val intent = Intent(this@CategoryActivity, BookingActivity::class.java)

        intent.putExtra("Event", event)

        // Start the BookingActivity
        startActivity(intent)
    }
}
