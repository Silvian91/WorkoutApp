package com.rosianu.workoutnotebook.data.openweathermap

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import com.rosianu.workoutnotebook.http.openweathermap.OpenWeatherMapApiService
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