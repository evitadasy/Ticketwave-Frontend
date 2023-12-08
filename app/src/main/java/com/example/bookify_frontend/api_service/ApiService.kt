package com.example.bookify_frontend.api_service

import com.example.bookify_frontend.model.City
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

    @GET("events/city/{city}")
    fun getEventsByCity(
        @Path("city") city: String
    ): Call<List<Event>>

    @GET("events/type/{type}/{city}")
    fun getEventsByTypeAndCity(
        @Path("type") type: String,
        @Path("city") city: String
    ): Call<List<Event>>

    @GET("cities")
    fun getCities(): Call<List<City>>
}