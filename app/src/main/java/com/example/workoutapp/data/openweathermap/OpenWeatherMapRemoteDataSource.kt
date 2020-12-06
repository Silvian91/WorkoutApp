package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.model.WeatherModel
import io.reactivex.Single

interface OpenWeatherMapRemoteDataSource {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}