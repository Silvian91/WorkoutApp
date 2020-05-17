package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import io.reactivex.Single

interface OpenWeatherMapRemoteDataSource {

    fun getCurrentWeather(): Single<WeatherModel>

}