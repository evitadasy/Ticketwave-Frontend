package com.example.testing

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("_id")
    val _id: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("tickets")
    val tickets: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("category")
    val category: String?
)