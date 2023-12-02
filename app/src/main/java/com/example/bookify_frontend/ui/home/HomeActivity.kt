package com.example.bookify_frontend.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.model.Event
import com.example.bookify_frontend.model.EventsResponse
import com.squareup.picasso.Picasso
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Target
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var basetext: TextView
lateinit var btn: Button

const val URL = "https://bookify-zm4t.onrender.com/"

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
//                Toast.makeText(this@HomeActivity, "GOOD", Toast.LENGTH_SHORT).show()
                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val eventsList = eventsResponse.events
                    // Handle the list of events as needed
                    displayEvents(eventsList)
                } else {
                    Toast.makeText(this@HomeActivity, "Response body is null", Toast.LENGTH_SHORT).show()
                }            }

            override fun onFailure(call: Call<EventsResponse?>, t: Throwable) {
                val errorMessage = t.message
                Toast.makeText(this@HomeActivity, "Failed: $errorMessage", Toast.LENGTH_SHORT).show()            }
        })


    }

    private fun displayEvents(eventsList: List<Event>) {
        // Assuming you have a TextView in your layout with id 'textView'
        val textView = findViewById<TextView>(R.id.textView)

        // Create a StringBuilder to build the display text
        val displayText = StringBuilder()

        for (event in eventsList) {
            // Append event details to the display text
            displayText.append("Event Title: ${event.title}\n")
            displayText.append("Event Date: ${event.date}\n")
            displayText.append("Description: ${event.description}\n")

//            // Load and display the image using Picasso
//            event.img?.let {
//                Picasso.get().load(it).into(object : Target {
//                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                        // Assuming you have an ImageView in your layout with id 'imageView'
//                        val imageView = findViewById<ImageView>(R.id.imageView)
//                        imageView.setImageBitmap(bitmap)
//                    }
//
//                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
//                        // Handle the case where image loading fails
//                    }
//
//                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                        // Handle the case where the image is still loading
//                    }
//                })
//            }

            displayText.append("\n\n")

        }

        // Set the display text in the TextView
        textView.text = displayText.toString()
    }
}