package com.example.studentorganizer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun AssignmentCard(
    title: String,
    course: String,
    dueDate: String,
    priority: String,
    isCompleted: Boolean,
    onToggleComplete: () -> Unit
) {
    // Badge color depends on priority
    val badgeColor = when (priority) {
        "High" -> Color(0xFFD32F2F)
        "Medium" -> Color(0xFFF57C00)
        else -> Color(0xFF388E3C)
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(checked = isCompleted, onCheckedChange = { onToggleComplete() })
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else null
                )
                Text("$course  |  Due: $dueDate", style = MaterialTheme.typography.bodySmall)
            }
            Surface(color = badgeColor, shape = RoundedCornerShape(8.dp)) {
                Text(
                    priority,
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}
