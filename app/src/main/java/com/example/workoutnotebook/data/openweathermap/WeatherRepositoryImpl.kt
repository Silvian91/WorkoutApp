package com.example.workoutnotebook.data.openweathermap

import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.WeatherRepository
import com.example.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

class WeatherRepositoryImpl(private val openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource) :
    WeatherRepository {

    override fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel> {
        return openWeatherMapRemoteDataSource.getCurrentWeather(locationModel)
    }

}