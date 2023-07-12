package com.example.nycschools.repository

import com.example.nycschools.model.SatScores
import com.example.nycschools.model.Schools
import retrofit2.http.GET

interface SchoolsService {
    @GET("s3k6-pzi2.json?" + "$" + "query=SELECT%0A%20%20%60dbn%60%2C%0A%20%20%60school_name%60")
    suspend fun getSchools(): List<Schools>

    @GET("f9bf-2cp4.json?" + "$" + "query=SELECT%0A%20%20%60dbn%60%2C%0A%20%20%60school_name%60%2C%0A%20%20%60num_of_sat_test_takers%60%2C%0A%20%20%60sat_critical_reading_avg_score%60%2C%0A%20%20%60sat_math_avg_score%60%2C%0A%20%20%60sat_writing_avg_score%60")
    suspend fun getSatScores(): List<SatScores>
}