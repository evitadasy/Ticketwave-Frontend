package com.example.bookify_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.model.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var basetext: TextView
lateinit var btn: Button

const val URL = "http://192.168.1.83:3000/"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        basetext= findViewById(R.id.textView)
        btn = findViewById(R.id.button)

        btn.setOnClickListener { getData() }

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
                Toast.makeText(this@HomeActivity, "GOOD", Toast.LENGTH_SHORT).show()
                val eventsResponse = response.body()
                if (eventsResponse != null) {
                    val eventsArray = eventsResponse.events
                    for (eventElement in eventsArray) {
                        val eventObject = eventElement.asJsonObject
                        // Extract properties from the eventObject
                        val eventId = eventObject.get("_id").asString
                        val eventType = eventObject.get("type").asString
                        Toast.makeText(this@HomeActivity, eventId+eventType, Toast.LENGTH_SHORT).show()

                        // Extract other properties...
                        // Create an Event object or process the data as needed
                    }
                }
            }

            override fun onFailure(call: Call<EventsResponse?>, t: Throwable) {
                val errorMessage = t.message
                Toast.makeText(this@HomeActivity, "Failed: $errorMessage", Toast.LENGTH_SHORT).show()            }
        })


    }
}