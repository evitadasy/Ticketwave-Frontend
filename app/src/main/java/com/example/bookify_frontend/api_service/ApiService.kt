package com.example.bookify_frontend.api_service

import com.example.bookify_frontend.model.CitiesResponse
import com.example.bookify_frontend.model.EventsResponse
import com.example.testing.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
//    @GET("events")
//    fun getEvents(
//        @Path("")
//    ): Call<List<Event>>

    @GET("events/type/{type}")
    fun getEventsByType(
        @Path("type") type: String
    ): Call<List<Event>>

    @GET("cities")
    fun getCities(): Call<CitiesResponse>
}