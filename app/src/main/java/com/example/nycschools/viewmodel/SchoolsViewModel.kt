package com.example.nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.Schools
import com.example.nycschools.repository.SchoolsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class SchoolsViewModel : ViewModel() {
    private val repository = SchoolsRepository()

    private val _schools = MutableStateFlow<List<Schools>>(emptyList())
    val schools: StateFlow<List<Schools>> = _schools

    // We match up the DBN from both our endpoints and fill in the SAT score data into each school.
    fun fetchSchools() {
        viewModelScope.launch {
            try {
                val schools = repository.getSchools()
                val satScores = repository.getSatScores()
                val updatedSchools = schools.mapNotNull { school ->
                    val matchingSatScores = satScores.find { it.dbn == school.dbn }
                    matchingSatScores?.let { school.copy(satScores = it) }
                }
                _schools.emit(updatedSchools)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}