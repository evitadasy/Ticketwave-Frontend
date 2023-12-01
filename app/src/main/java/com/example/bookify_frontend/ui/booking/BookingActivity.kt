import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookify_frontend.R

// Class definition
class BookingActivity : AppCompatActivity() {

    // onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the layout defined in activity_booking.xml
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