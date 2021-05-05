package com.rosianu.workoutnotebook.domain.weather

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

interface WeatherRepository {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}