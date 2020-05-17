package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.http.openweathermap.OpenWeatherMapApiService
import io.reactivex.Single

class OpenWeatherMapRemoteDataSourceImpl(
    private val openWeatherMapApiService: OpenWeatherMapApiService
) : OpenWeatherMapRemoteDataSource {

    override fun getCurrentWeather(): Single<WeatherModel> {
        return openWeatherMapApiService.getCurrentWeather("Berlin", "en", "metric")
            .map { it.toModel() }
    }

}