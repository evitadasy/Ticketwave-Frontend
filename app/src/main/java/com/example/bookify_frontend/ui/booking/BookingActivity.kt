package com.example.bookify_frontend.ui.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.bookify_frontend.R

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Find the "Book Now" button in the layout
        val bookNowButton: Button = findViewById(R.id.button3)

        // Set a click listener for the button
        bookNowButton.setOnClickListener {
            // Code to be executed when the "Book Now" button is clicked
            Toast.makeText(this, "Book Now Button Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}