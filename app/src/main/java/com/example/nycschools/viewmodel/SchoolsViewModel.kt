package com.example.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
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
    fun fetchSchools() {
        viewModelScope.launch {
            try {
                val schools = repository.getSchools()
                _schools.emit(schools)
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

}