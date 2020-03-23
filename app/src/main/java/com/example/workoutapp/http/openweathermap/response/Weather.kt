package com.example.workoutapp.http.openweathermap.response


import com.google.gson.annotations.SerializedName

data class Weather(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = ""
)