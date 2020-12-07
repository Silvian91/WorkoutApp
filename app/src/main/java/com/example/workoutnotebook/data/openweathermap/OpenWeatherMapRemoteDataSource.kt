package com.example.workoutnotebook.data.openweathermap

import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

interface OpenWeatherMapRemoteDataSource {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}