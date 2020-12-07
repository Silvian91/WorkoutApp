package com.example.workoutnotebook.domain.workout.model

data class WorkoutModel(
    val id: Long? = null,
    val title: String,
    val userId: Long
)