package com.rosianu.workoutnotebook.http.openweathermap.response


data class Weather(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)