package com.example.bookify_frontend.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String
)
