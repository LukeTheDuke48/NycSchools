package com.example.nycschools

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nycschools.model.Schools
import com.example.nycschools.viewmodel.SchoolsViewModel
import com.example.nycschools.ui.theme.*


@Composable
fun SchoolsScreen(viewModel: SchoolsViewModel) {
    val schools by viewModel.schools.collectAsState()
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.fetchSchools()
    }

    if (schools.isNullOrEmpty()) {
        LoadingScreen()
    } else {
        Box(Modifier.fillMaxSize()) {
            LazyColumn(
                state = scrollState
            ) {
                items(schools) { school ->
                    SchoolItem(school = school)
                }
            }
        }
    }
}

@Composable
fun SchoolItem(school: Schools) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Cream,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Accordion(title = school.school_name) {
            Column(Modifier.padding(16.dp)) {

                val satScores = school.satScores
                if (satScores != null) {
                    Text(
                        text = "Total SAT Test Takers: ${satScores.num_of_sat_test_takers}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Ebony,
                    )
                    Text(
                        text = "SAT Critical Reading Average Score: ${satScores.sat_critical_reading_avg_score}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Ebony,
                    )
                    Text(
                        text = "SAT Math Average Score: ${satScores.sat_math_avg_score}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Ebony,
                    )
                    Text(
                        text = "SAT Writing Average Score : ${satScores.sat_writing_avg_score}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Ebony,
                    )
                }
            }
        }
    }
}

@Composable
fun Accordion(
    title: String,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Ebony
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp),
                color = Ebony
            )
        }

        AnimatedVisibility(visible = expanded) {
            content()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Tan)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Ebony
        )
    }
}



@Preview()
@Composable
fun SchoolsScreenPreview() {
    SchoolsScreen(viewModel = SchoolsViewModel())
}

