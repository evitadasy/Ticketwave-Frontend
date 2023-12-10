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
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@Suppress("DEPRECATION")
class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val event = intent.getParcelableExtra<Event>("Event")
        val titleTextView = findViewById<TextView>(R.id.event_title)
        val dateTextView = findViewById<TextView>(R.id.day)
        val locationTextView = findViewById<TextView>(R.id.location)
        val timeTextView = findViewById<TextView>(R.id.hour)
        val descriptionTextView = findViewById<TextView>(R.id.details_text)
        val priceTextView = findViewById<TextView>(R.id.price)
        val pricingdetailsTextView = findViewById<TextView>(R.id.pricing_text)
        val imageView = findViewById<ImageView>(R.id.eventImg)
        val imageUrl: String? = event?.img


        event?.let {
            titleTextView.text = it.title
            descriptionTextView.text = it.description
            val priceValue = it.price.toString()
            priceTextView.text = "Ticket costs "+priceValue+"€"
            locationTextView.text = it.location +", "+ it.city
            pricingdetailsTextView.text = it.pricingdetails

            //Format Date
            // Parse the input date string
            val instant = Instant.parse(it.date)
            val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

            // Format for the hourText (e.g., 5pm/3am)
            val hourFormatter = DateTimeFormatter.ofPattern("ha", Locale.UK)
            val formattedHour = localDateTime.format(hourFormatter)

            // Format for the dayText (e.g., 12 Mar 2023)
            val dayFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.UK)
            val formattedDay = localDateTime.format(dayFormatter)


            dateTextView.text = formattedDay
            timeTextView.text = formattedHour
        }

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(imageView)



            // Find the "Book Now" button in the layout
            val bookNowButton: Button = findViewById(R.id.bookBtn)

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
