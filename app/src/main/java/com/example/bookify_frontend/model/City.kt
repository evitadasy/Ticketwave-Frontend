package com.example.bookify_frontend.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("name")
    val cityName: String
)
