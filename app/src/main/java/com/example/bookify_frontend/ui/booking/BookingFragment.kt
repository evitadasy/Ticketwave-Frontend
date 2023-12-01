package com.example.bookify_frontend.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bookify_frontend.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BookingFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_booking, container, false)

        // Find the "Book Now" button in the layout
        val bookNowButton: Button = rootView.findViewById(R.id.button3)

        // Set a click listener for the button
        bookNowButton.setOnClickListener {
            // Code to be executed when the "Book Now" button is clicked
            Toast.makeText(requireContext(), "Book Now Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
