package com.example.workoutapp.domain.openweathermap

import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import io.reactivex.Single

interface WeatherRepository {

    fun getCurrentWeather(): Single<WeatherModel>

}