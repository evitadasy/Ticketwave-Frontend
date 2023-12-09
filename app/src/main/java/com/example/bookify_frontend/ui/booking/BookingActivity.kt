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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


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
                // Create an Intent to start the ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)

                // Start the ConfirmationActivity
                startActivity(intent)
            }
        }
    }
