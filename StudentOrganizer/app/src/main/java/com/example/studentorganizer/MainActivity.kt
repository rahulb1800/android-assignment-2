package com.example.studentorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentorganizer.components.BottomBar
import com.example.studentorganizer.model.sampleAssignments
import com.example.studentorganizer.navigation.Screen
import com.example.studentorganizer.screens.AddAssignmentScreen
import com.example.studentorganizer.screens.AssignmentsScreen
import com.example.studentorganizer.screens.DashboardScreen
import com.example.studentorganizer.screens.ProfileScreen
import com.example.studentorganizer.screens.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentOrganizerApp()
        }
    }
}

@Composable
fun StudentOrganizerApp() {
    var darkMode by remember { mutableStateOf(false) }
    val assignments = remember { mutableStateListOf(*sampleAssignments.toTypedArray()) }

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // Flip the completed flag of one assignment
    val toggleAssignment: (Int) -> Unit = { id ->
        val index = assignments.indexOfFirst { it.id == id }
        if (index >= 0) {
            assignments[index] = assignments[index].copy(isCompleted = !assignments[index].isCompleted)
        }
    }

    val goToAdd = { navController.navigate(Screen.AddAssignment.route) }

    MaterialTheme(colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()) {
        Scaffold(
            bottomBar = {
                // Hide the bottom bar on the add screen
                if (currentRoute != Screen.AddAssignment.route) {
                    BottomBar(navController, currentRoute)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Dashboard.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Dashboard.route) {
                    DashboardScreen(assignments, toggleAssignment) { goToAdd() }
                }
                composable(Screen.Assignments.route) {
                    AssignmentsScreen(assignments, toggleAssignment) { goToAdd() }
                }
                composable(Screen.Profile.route) {
                    ProfileScreen()
                }
                composable(Screen.Settings.route) {
                    SettingsScreen(darkMode) { darkMode = it }
                }
                composable(Screen.AddAssignment.route) {
                    AddAssignmentScreen(
                        onSave = { assignments.add(it.copy(id = assignments.size + 1)) },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
