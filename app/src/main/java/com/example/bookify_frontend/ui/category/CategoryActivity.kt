package com.example.bookify_frontend.ui.category

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.api_service.RetrofitService
import com.example.testing.CategoryAdapter
import com.example.testing.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    data class CategoryItem(val imageUrl: String, val title: String, val description: String)

    private val categoryItemList = mutableListOf<CategoryItem>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Get the selected category position from intent
        val categoryPosition = intent.getIntExtra("CATEGORY_POSITION", -1)

        if (categoryPosition != -1) {
            // Make an API call to fetch data for the selected category
            fetchDataForCategory(categoryPosition)
        } else {
            // Handle error or default behavior
        }
    }

    private fun fetchDataForCategory(categoryPosition: Int) {
        val apiService = RetrofitService.createApiService()

        val selectedCategory = when(categoryPosition) {
            0 -> "cinema"
            1 -> "live"
            2 -> "food"
            3 -> "sports"
            4 -> "seminar"
            5 -> "theatre"
            6 -> "kids"
            else -> "museums"
        }

        val retrofitAPI = apiService.getEventsByType(selectedCategory.capitalize(Locale.ROOT))

        retrofitAPI.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(
                call: Call<List<Event>?>,
                response: Response<List<Event>?>
            ) {
                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@CategoryActivity)
                    categoryAdapter = CategoryAdapter(eventsResponse, this@CategoryActivity)
                    recyclerView.adapter = categoryAdapter

                    // Filter events based on the selected category
//                    val filteredEvents = when (categoryPosition) {
//                        0 -> eventsList.filter { it.category == "Cinema" }
//                        1 -> eventsList.filter { it.category == "Concert" }
//                        2 -> eventsList.filter { it.category == "Food" }
//                        3 -> eventsList.filter { it.category == "Sports"  }
//                        4 -> eventsList.filter { it.category == "Seminar"}
//                        5 -> eventsList.filter { it.category == "Theatre" }
//                        6 -> eventsList.filter { it.category == "Kids"  }
//                        7 -> eventsList.filter { it.category == "Museums" }
//
//                        // Add more cases for other categories
//                        else -> emptyList()
//                    }

                    // Update the categoryItemList and notify adapter
                    //     categoryItemList.clear()
//                    for (event in filteredEvents) {
//                        categoryItemList.add(
//                            CategoryItem(
//                                event.img ?: "",
//                                event.title ?: "",
//                                event.description ?: ""
//                            )
//                        )
//                    }
                    categoryAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                // Handle failure
            }

        })
    }

    // Implement the onItemClick method
    override fun onItemClick(event: Event) {
        // Handle the click event here
        Toast.makeText(this, "Clicked on event: ${event.title}", Toast.LENGTH_SHORT).show()
    }
}