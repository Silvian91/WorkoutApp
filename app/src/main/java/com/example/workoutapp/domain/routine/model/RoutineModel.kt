package com.example.workoutapp.domain.routine.model

data class RoutineModel(
    var routineName: String,
    var sets: String,
    var reps: String,
    var weight: String,
    var weightMeasurement: String,
    var rest: String
)