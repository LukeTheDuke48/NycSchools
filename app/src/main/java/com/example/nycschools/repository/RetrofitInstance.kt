package com.example.nycschools.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL = "https://data.cityofnewyork.us/resource/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val schoolsService: SchoolsService by lazy {
        retrofit.create(SchoolsService::class.java)
    }
}