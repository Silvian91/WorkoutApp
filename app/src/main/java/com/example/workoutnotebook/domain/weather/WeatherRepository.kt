package com.example.workoutnotebook.domain.weather

import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

interface WeatherRepository {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}