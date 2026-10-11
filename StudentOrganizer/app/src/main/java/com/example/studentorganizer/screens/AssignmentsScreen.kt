package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentCard
import com.example.studentorganizer.model.Assignment

@Composable
fun AssignmentsScreen(
    assignments: List<Assignment>,
    onToggle: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    var filter by rememberSaveable { mutableStateOf("All") }

    // Pick which assignments to show
    val visible = when (filter) {
        "Pending" -> assignments.filter { !it.isCompleted }
        "Completed" -> assignments.filter { it.isCompleted }
        else -> assignments
    }

    Scaffold(
        topBar = { AppBar("My Assignments", false, {}) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Assignment")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("All", "Pending", "Completed").forEach { option ->
                    RadioButton(selected = filter == option, onClick = { filter = option })
                    Text(option, modifier = Modifier.padding(end = 12.dp))
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(visible, key = { it.id }) { a ->
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
