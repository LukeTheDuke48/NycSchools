package com.example.nycschools.model

data class Schools (
    val dbn: String,
    val school_name: String,
    val satScores: SatScores? = null
)
