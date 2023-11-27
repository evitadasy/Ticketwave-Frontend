package com.example.bookify_frontend.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class EventsResponse(
    val events: List<Event>
)