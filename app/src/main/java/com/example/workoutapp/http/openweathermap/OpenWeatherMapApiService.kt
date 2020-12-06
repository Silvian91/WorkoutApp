package com.example.workoutapp.http.openweathermap

import com.example.workoutapp.http.openweathermap.response.OpenWeatherMapResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApiService {

    @GET("weather")
    fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") languageCode: String = "en",
        @Query("units") units: String = "metric"
    ): Single<OpenWeatherMapResponse>

}