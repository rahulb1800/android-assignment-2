package com.example.studentorganizer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// State is hoisted: values come in, changes go out through callbacks
@Composable
fun AssignmentForm(
    title: String,
    course: String,
    dueDate: String,
    priority: String,
    description: String,
    reminder: Boolean,
    onTitleChange: (String) -> Unit,
    onCourseChange: (String) -> Unit,
    onDueDateChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onReminderChange: (Boolean) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text("Assignment Title *") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = course,
            onValueChange = onCourseChange,
            label = { Text("Course Code * (e.g. CS101)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dueDate,
            onValueChange = onDueDateChange,
            label = { Text("Due Date * (e.g. 15 Oct 2026)") },
            singleLine = true,
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Priority Level")
        Row(verticalAlignment = Alignment.CenterVertically) {
            listOf("Low", "Medium", "High").forEach { option ->
                RadioButton(selected = priority == option, onClick = { onPriorityChange(option) })
                Text(option, modifier = Modifier.padding(end = 12.dp))
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text("Description / Notes") },
            minLines = 3,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = reminder, onCheckedChange = onReminderChange)
            Text("Enable Reminder Notification")
        }
    }
}
