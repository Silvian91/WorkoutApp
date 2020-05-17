package com.example.workoutapp.domain.openweathermap

import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import io.reactivex.Single

interface OpenWeatherMapRepository {

    fun getCurrentWeather(): Single<WeatherModel>

}