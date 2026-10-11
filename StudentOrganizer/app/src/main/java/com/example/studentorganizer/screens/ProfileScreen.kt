package com.example.studentorganizer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentorganizer.R
import com.example.studentorganizer.components.AppBar
import com.example.studentorganizer.components.StudentProfile

@Composable
fun ProfileScreen() {
    Scaffold(topBar = { AppBar("Student Profile", false, {}) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            StudentProfile(
                studentName = "Rahul Bellary",
                studentId = "STU-2024-001",
                major = "Computer Science",
                avatarRes = R.drawable.ic_avatar
            )

            // Academic stats
            Card(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Academic Stats", style = MaterialTheme.typography.titleMedium)
                    Text("GPA: 3.82", modifier = Modifier.padding(top = 8.dp))
                    Text("Completed Credits: 84")
                    Text("Current Semester: Fall 2026")
                }
            }

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Edit Profile")
            }
            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Export Academic Summary")
            }
        }
    }
}
