package com.example.bookify_frontend.ui.booking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bookify_frontend.R
import com.example.bookify_frontend.ui.confirmation.ConfirmationActivity

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

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