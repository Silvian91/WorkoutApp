package com.example.workoutapp.domain.routine.model

data class RoutineModel(
    val routineName: String,
    val sets: String,
    val reps: String,
    val weight: String,
    val weightMeasurement: String,
    val rest: String,
    val workoutId: Long,
    val userId: Long
)