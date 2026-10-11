package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentCard
import com.example.studentorganizer.components.CourseInfo
import com.example.studentorganizer.model.Assignment
import com.example.studentorganizer.model.sampleCourses

@Composable
fun DashboardScreen(
    assignments: List<Assignment>,
    onToggle: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val pending = assignments.filter { !it.isCompleted }

    Scaffold(
        topBar = { AppBar("Welcome back, Rahul!", false, {}) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Assignment")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Quick stats
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                StatCard("Active Courses", sampleCourses.size, Modifier.weight(1f))
                StatCard("Pending Tasks", pending.size, Modifier.weight(1f))
            }

            Text(
                "Active Courses",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(sampleCourses) { course ->
                    CourseInfo(course.name, course.code, course.instructor, course.progress)
                }
            }

            Text(
                "Urgent Deadlines",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                pending.take(2).forEach { a ->
                    AssignmentCard(
                        title = a.title,
                        course = a.course,
                        dueDate = a.dueDate,
                        priority = a.priority,
                        isCompleted = a.isCompleted,
                        onToggleComplete = { onToggle(a.id) }
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(label: String, count: Int, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text("($count)", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
