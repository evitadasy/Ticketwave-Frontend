package com.example.bookify_frontend.model

import com.example.testing.Event

data class Booking (
    val id: String,
    val event: Event,
    val quantity: Number
)

