package com.rosianu.workoutnotebook.data.openweathermap

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.WeatherRepository
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import io.reactivex.Single

class WeatherRepositoryImpl(private val openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource) :
    WeatherRepository {

    override fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel> {
        return openWeatherMapRemoteDataSource.getCurrentWeather(locationModel)
    }

}