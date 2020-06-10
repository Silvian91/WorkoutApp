package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.openweathermap.WeatherRepository
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import io.reactivex.Single

class WeatherRepositoryImpl(private val openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource) :
    WeatherRepository {

    override fun getCurrentWeather(): Single<WeatherModel> {
        return openWeatherMapRemoteDataSource.getCurrentWeather()
    }

}