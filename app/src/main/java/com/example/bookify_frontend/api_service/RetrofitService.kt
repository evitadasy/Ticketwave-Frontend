package com.example.bookify_frontend.api_service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://bookify-zm4t.onrender.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun createApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}