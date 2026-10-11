package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.AssignmentForm
import com.example.studentorganizer.model.Assignment

@Composable
fun AddAssignmentScreen(onSave: (Assignment) -> Unit, onBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Medium") }
    var description by remember { mutableStateOf("") }
    var reminder by remember { mutableStateOf(false) }

    // Save is allowed only when required fields are filled
    val canSave = title.isNotBlank() && course.isNotBlank() && dueDate.isNotBlank()

    Scaffold(topBar = { AppBar("Add New Assignment", true, onBack) }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            item {
                AssignmentForm(
                    title = title,
                    course = course,
                    dueDate = dueDate,
                    priority = priority,
                    description = description,
                    reminder = reminder,
                    onTitleChange = { title = it },
                    onCourseChange = { course = it },
                    onDueDateChange = { dueDate = it },
                    onPriorityChange = { priority = it },
                    onDescriptionChange = { description = it },
                    onReminderChange = { reminder = it }
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            // The id is set in MainActivity
                            onSave(Assignment(0, title, course, dueDate, priority, description, reminder))
                            onBack()
                        },
                        enabled = canSave,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save Assignment")
                    }
                }
            }
        }
    }
}
