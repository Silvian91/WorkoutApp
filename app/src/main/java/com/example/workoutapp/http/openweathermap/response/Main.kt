package com.example.workoutapp.http.openweathermap.response


import com.google.gson.annotations.SerializedName

data class Main(
    val humidity: Int = 0,
    val pressure: Int = 0,
    val temp: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0,
    @SerializedName("temp_min")
    val tempMin: Double = 0.0
)