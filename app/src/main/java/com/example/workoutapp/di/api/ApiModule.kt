package com.example.workoutapp.di.api

import android.content.Context
import com.example.workoutapp.http.ApiUtil
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
import com.example.workoutapp.http.openweathermap.OpenWeatherMapApiService
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun providesChuckNorrisApiService(context: Context): ChuckNorrisApiService {
        return ApiUtil.buildRetrofit(
            ApiUtil.buildChuckNorrisOkHttpClient(context),
            "https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com"
        ).create(ChuckNorrisApiService::class.java)
    }

    @Provides
    fun providesOpenWeatherMapApiService(context: Context): OpenWeatherMapApiService {
        return ApiUtil.buildRetrofit(
            ApiUtil.buildOpenWeatherOkHttpClient(context),
            "https://community-open-weather-map.p.rapidapi.com"
        ).create(OpenWeatherMapApiService::class.java)
    }
}