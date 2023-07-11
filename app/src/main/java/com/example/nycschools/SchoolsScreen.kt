package com.example.nycschools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nycschools.viewmodel.SchoolsViewModel
import org.jetbrains.annotations.TestOnly


@Composable
fun SchoolsScreen(viewModel: SchoolsViewModel) {
    val schools = viewModel.schools.value

    LaunchedEffect(Unit) {
        viewModel.fetchSchools()
    }

    Column {
        if (schools.isNullOrEmpty()) {
            Text(text = "Loading...")
        } else {
             schools.forEach() { school ->
                 Text(text = school.dbn)
                 Text(text = school.school_name)
             }
        }
    }
}

@Preview()
@Composable
fun SchoolsScreenPreview(){
    SchoolsScreen(viewModel = SchoolsViewModel())
}
