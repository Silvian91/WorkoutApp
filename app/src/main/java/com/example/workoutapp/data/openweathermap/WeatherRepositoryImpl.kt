package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.WeatherRepository
import com.example.workoutapp.domain.weather.model.WeatherModel
import io.reactivex.Single

class WeatherRepositoryImpl(private val openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource) :
    WeatherRepository {

    override fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel> {
        return openWeatherMapRemoteDataSource.getCurrentWeather(locationModel)
    }

}