package com.example.bookify_frontend.ui.confirmation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bookify_frontend.R
import com.example.bookify_frontend.ui.category.CategoryActivity
import com.example.bookify_frontend.ui.home.HomeActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val btnExit: Button = findViewById(R.id.btnExit)
        btnExit.setOnClickListener {
            navigateToHomeActivity()
        }

    }

    private fun navigateToHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}