package com.example.studentorganizer.model

data class Assignment(
    val id: Int,
    val title: String,
    val course: String,
    val dueDate: String,
    val priority: String,
    val description: String = "",
    val reminder: Boolean = false,
    val isCompleted: Boolean = false
)

data class Course(
    val name: String,
    val code: String,
    val instructor: String,
    val progress: Float
)

// Sample data used instead of a database
val sampleCourses = listOf(
    Course("Data Structures", "CS101", "Dr. Sharma", 0.65f),
    Course("Calculus II", "MATH201", "Prof. Smith", 0.40f),
    Course("Technical Writing", "ENG102", "Dr. Iyer", 0.80f),
    Course("Quantum Physics", "PHY103", "Prof. Kumar", 0.55f)
)

val sampleAssignments = listOf(
    Assignment(1, " DSA Lab", "CS101", "12 Oct 2026", "High"),
    Assignment(2, "Integration Worksheet", "MATH201", "14 Oct 2026", "High"),
    Assignment(3, "Essay Draft", "ENG102", "16 Oct 2026", "Low"),
    Assignment(4, "Experiment Report", "PHY101", "18 Oct 2026", "Medium"),
    Assignment(5, "Binary Tree Quiz Prep", "CS101", "20 Oct 2026", "High"),
    Assignment(6, "Matrices", "MATH201", "22 Oct 2026", "Low"),
    Assignment(7, "Presentation", "ENG102", "25 Oct 2026", "Medium"),
    Assignment(8, "Mechanics Notes", "PHY101", "05 Oct 2026", "Low", isCompleted = true)
)
