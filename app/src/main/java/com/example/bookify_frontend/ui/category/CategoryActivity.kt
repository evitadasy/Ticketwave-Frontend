package com.example.bookify_frontend.ui.category

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.model.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CategoryActivity : AppCompatActivity() {

    data class CategoryItem(val imageUrl: String, val title: String, val description: String)


    private val categoryItemList = mutableListOf<CategoryItem>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(categoryItemList)
        recyclerView.adapter = categoryAdapter

        // Make an API call to fetch data
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        // Adjust the URL and API call based on your actual API endpoint and structure
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("YOUR_API_BASE_URL")
            .build()

        val retrofitAPI = retrofit.create(ApiService::class.java).getEvents()

        retrofitAPI.enqueue(object : Callback<EventsResponse?> {
            override fun onResponse(call: Call<EventsResponse?>, response: Response<EventsResponse?>) {
                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val eventsList = eventsResponse.events

                    // edw allazoume ti list na fainetai sto recyclerview.
                    categoryItemList.clear()
                    for (event in eventsList) {
                        categoryItemList.add(CategoryItem(event.img ?: "", event.title, event.description))
                    }
                    categoryAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<EventsResponse?>, t: Throwable) {
                // Handle failure
            }
        })
    }
}