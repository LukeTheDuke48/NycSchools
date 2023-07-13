package com.example.nycschools.repository

import com.example.nycschools.model.SatScores
import com.example.nycschools.model.Schools

class SchoolsRepository {
    private val schoolsService = RetrofitInstance.schoolsService

    // I never really saw this in practice (Sorting our data in the repository), but for the sake of
    // the project, it'd be nice if the schools were in alphabetical order.
    suspend fun getSchools(): List<Schools> {
        val schools = schoolsService.getSchools()
        return schools.sortedBy { it.school_name }
    }

    suspend fun getSatScores(): List<SatScores> {
        val satScoresList = schoolsService.getSatScores()
        return satScoresList.map { transformSatScores(it) }
    }

    // Since some of our data in our API is coming back as "s", we are going to handle it here.
    // Instead, the user will see "N/A"
    private fun transformSatScores(satScores: SatScores): SatScores {
        return satScores.copy(
            num_of_sat_test_takers = if (satScores.num_of_sat_test_takers == "s") "N/A" else satScores.num_of_sat_test_takers,
            sat_critical_reading_avg_score = if (satScores.sat_critical_reading_avg_score == "s") "N/A" else satScores.sat_critical_reading_avg_score,
            sat_math_avg_score = if (satScores.sat_math_avg_score == "s") "N/A" else satScores.sat_math_avg_score,
            sat_writing_avg_score = if (satScores.sat_writing_avg_score == "s") "N/A" else satScores.sat_writing_avg_score
        )
    }
}