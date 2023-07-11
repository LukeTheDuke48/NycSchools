package com.example.nycschools.repository

import com.example.nycschools.model.SatScores
import com.example.nycschools.model.Schools
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolsService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchools(
        @Query("\$query") query: String = "SELECT%0A%20%20%60dbn%60%2C%0A%20%20%60school_name%60"
    ): List<Schools>

    @GET("f9bf-2cp4.json")
    suspend fun getSatScores(): SatScores
}