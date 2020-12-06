package com.example.workoutapp.domain.weather

import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.model.WeatherModel
import io.reactivex.Single

interface WeatherRepository {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}