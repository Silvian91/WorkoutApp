package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.openweathermap.OpenWeatherMapRepository
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import io.reactivex.Single

class OpenWeatherMapRepositoryImpl(private val openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource) :
    OpenWeatherMapRepository {

    override fun getCurrentWeather(): Single<WeatherModel> {
        return openWeatherMapRemoteDataSource.getCurrentWeather()
    }

}