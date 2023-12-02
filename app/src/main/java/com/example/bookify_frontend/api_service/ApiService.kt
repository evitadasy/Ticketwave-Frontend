package com.example.bookify_frontend.api_service

import com.example.bookify_frontend.model.CitiesResponse
import com.example.bookify_frontend.model.EventsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("events")
    fun getEvents(): Call<EventsResponse>

//    @GET("events/type/{type}")
//    fun getEventsByType(): Call<EventsResponse>

    @GET("cities")
    fun getCities(): Call<CitiesResponse>
}