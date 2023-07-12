package com.example.nycschools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nycschools.model.Schools
import com.example.nycschools.viewmodel.SchoolsViewModel
import org.jetbrains.annotations.TestOnly


@Composable
fun SchoolsScreen(viewModel: SchoolsViewModel) {
    val schools by viewModel.schools.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchSchools()
    }

    if (schools.isNullOrEmpty()) {
        Text(text = "Loading...")
    } else {
        LazyColumn(Modifier.fillMaxSize()) {
            items(schools) { school ->
                SchoolItem(school = school)
            }
        }
    }
}

@Composable
fun SchoolItem(school: Schools) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { /* Handle click event */ },
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = school.school_name,
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = school.dbn,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview()
@Composable
fun SchoolsScreenPreview(){
    SchoolsScreen(viewModel = SchoolsViewModel())
}

