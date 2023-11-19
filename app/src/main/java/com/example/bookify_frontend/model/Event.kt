package com.example.bookify_frontend.model

import java.sql.Date


////////////TSEKARE DATE
data class Event (
    val id: String,
    val title: String,
    val description: String,
    val date: Date,
    val price: Number,
    val city: String,
    val location: String,
    val img: String,
    val tickets: Number
)

