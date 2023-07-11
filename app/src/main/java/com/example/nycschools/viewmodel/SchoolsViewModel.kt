package com.example.nycschools.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.model.Schools
import com.example.nycschools.repository.SchoolsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SchoolsViewModel : ViewModel() {
    private val repository = SchoolsRepository()

    private val _schools = MutableLiveData<List<Schools>>()
    val schools : MutableLiveData<List<Schools>> = _schools

    fun fetchSchools(){
        viewModelScope.launch {
            try {
                val schools = repository.getSchools()
                _schools.value = schools
            }catch (e: Exception){

            }
        }
    }
}