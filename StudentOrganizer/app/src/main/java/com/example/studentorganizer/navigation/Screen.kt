package com.example.studentorganizer.navigation

// All routes of the app in one place
sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object Assignments : Screen("assignments")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object AddAssignment : Screen("add_assignment")
}
