package com.example.bookify_frontend.ui.booking

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookify_frontend.R
import com.example.bookify_frontend.ui.confirmation.ConfirmationActivity
import com.example.testing.Event
import com.squareup.picasso.Picasso
import android.content.Intent
import android.widget.Toast
import com.example.bookify_frontend.api_service.RetrofitService
import com.example.bookify_frontend.model.Booking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date


@Suppress("DEPRECATION")
class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val event = intent.getParcelableExtra<Event>("Event")
        val titleTextView = findViewById<TextView>(R.id.textView3)
        val dateTextView = findViewById<TextView>(R.id.textView7)
        val cityTextView = findViewById<TextView>(R.id.textView9)
        val placeTextView = findViewById<TextView>(R.id.textView2)
        val timeTextView = findViewById<TextView>(R.id.textView8)
        val descriptionTextView = findViewById<TextView>(R.id.textView6)
        val priceTextView = findViewById<TextView>(R.id.textView10)
        val pricingdetailsTextView = findViewById<TextView>(R.id.textView12)
        val imageView = findViewById<ImageView>(R.id.burger)
        val imageUrl: String? = event?.img



        event?.let {
            titleTextView.text = it.title
            cityTextView.text = it.city
            descriptionTextView.text = it.description
            priceTextView.text = it.price.toString()
            placeTextView.text = it.location
            pricingdetailsTextView.text = it.pricingdetails

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val outputDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputTimeFormat = SimpleDateFormat("HH:mm:ss")

            val date: Date? = inputFormat.parse(it.date)

            val formattedDate: String = date?.let { outputDateFormat.format(it) } ?: ""
            val formattedTime: String = date?.let { outputTimeFormat.format(it) } ?: ""

            dateTextView.text = formattedDate

            timeTextView.text = formattedTime
        }

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(imageView)



            // Find the "Book Now" button in the layout
            val bookNowButton: Button = findViewById(R.id.button3)

            // Set a click listener for the button
            bookNowButton.setOnClickListener {
                postBooking()
            }
        }

    private fun postBooking() {
        val event = intent.getParcelableExtra<Event>("Event")

        // Check if event is not null before proceeding
        if (event != null) {
            val booking = Booking(event.id, 1)

            val apiService = RetrofitService.createApiService()

            // Call the ApiService to post a booking
            val call: Call<Void> = apiService.postBooking(event.id, booking)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    // Handle success
                    if (response.isSuccessful) {
                        startConfirmationActivity()
                    } else {
                        // Handle error
                        Toast.makeText(applicationContext, "Booking failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle failure
                    Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun startConfirmationActivity() {
        // Create an Intent to start the ConfirmationActivity
        val intent = Intent(this, ConfirmationActivity::class.java)

        // Start the ConfirmationActivity
        startActivity(intent)
    }
}
