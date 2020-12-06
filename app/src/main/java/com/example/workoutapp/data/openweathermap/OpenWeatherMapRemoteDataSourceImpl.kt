package com.example.workoutapp.data.openweathermap

import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.model.WeatherModel
import com.example.workoutapp.http.openweathermap.OpenWeatherMapApiService
import io.reactivex.Single

class OpenWeatherMapRemoteDataSourceImpl(
    private val openWeatherMapApiService: OpenWeatherMapApiService
) : OpenWeatherMapRemoteDataSource {

    override fun getCurrentWeather(locationModel: LocationModel): Single<WeatherModel> {
        return openWeatherMapApiService.getCurrentWeather(
            locationModel.latitude,
            locationModel.longitude,
            "en",
            "metric"
        )
            .map { it.toModel() }
    }

}