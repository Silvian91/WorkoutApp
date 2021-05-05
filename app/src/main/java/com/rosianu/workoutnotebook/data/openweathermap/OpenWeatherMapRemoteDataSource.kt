package com.rosianu.workoutnotebook.data.openweathermap

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

interface OpenWeatherMapRemoteDataSource {

    fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel>

}