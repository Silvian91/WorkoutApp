package com.example.workoutapp.http.openweathermap.response


import com.google.gson.annotations.SerializedName

data class Coord(
    val lat: Double = 0.0,
    val lon: Double = 0.0
)