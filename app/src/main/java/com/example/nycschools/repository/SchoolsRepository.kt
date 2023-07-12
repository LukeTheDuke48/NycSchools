package com.example.nycschools.repository

import com.example.nycschools.model.SatScores
import com.example.nycschools.model.Schools

class SchoolsRepository {
    private val schoolsService = RetrofitInstance.schoolsService

    suspend fun getSchools(): List<Schools>{
        return  schoolsService.getSchools()
    }

    suspend fun getSatScores(): List<SatScores> {
        return schoolsService.getSatScores()
    }

}