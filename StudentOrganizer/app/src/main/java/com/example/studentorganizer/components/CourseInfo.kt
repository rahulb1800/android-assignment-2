package com.example.studentorganizer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CourseInfo(courseName: String, courseCode: String, instructor: String, progress: Float) {
    Card(modifier = Modifier.width(220.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(courseName, style = MaterialTheme.typography.titleMedium)
            Text(courseCode, style = MaterialTheme.typography.bodyMedium)
            Text(instructor, style = MaterialTheme.typography.bodySmall)
            Text(
                "Progress: ${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }
    }
}
