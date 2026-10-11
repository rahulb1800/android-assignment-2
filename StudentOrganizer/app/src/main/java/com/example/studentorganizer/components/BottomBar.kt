package com.example.studentorganizer.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.studentorganizer.navigation.Screen

private data class BottomItem(val label: String, val route: String, val icon: ImageVector)

private val bottomItems = listOf(
    BottomItem("Dashboard", Screen.Dashboard.route, Icons.Default.Home),
    BottomItem("Assignments", Screen.Assignments.route, Icons.AutoMirrored.Filled.List),
    BottomItem("Profile", Screen.Profile.route, Icons.Default.Person),
    BottomItem("Settings", Screen.Settings.route, Icons.Default.Settings)
)

@Composable
fun BottomBar(navController: NavHostController, currentRoute: String?) {
    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    // Switch tabs without stacking screens
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
