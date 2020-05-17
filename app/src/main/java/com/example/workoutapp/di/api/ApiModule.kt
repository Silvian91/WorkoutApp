package com.example.workoutapp.di.api

import android.content.Context
import com.example.workoutapp.http.ApiUtil
import com.example.workoutapp.http.inspirationalquote.InspirationalApiService
import com.example.workoutapp.http.openweathermap.OpenWeatherMapApiService
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun providesInspirationalQuoteApiService(context: Context): InspirationalApiService {
        return ApiUtil.buildRetrofit(
            ApiUtil.buildInspirationalQuotesOkHttpClient(context),
            "https://quotable-quotes.p.rapidapi.com"
        ).create(InspirationalApiService::class.java)
    }

    @Provides
    fun providesOpenWeatherMapApiService(context: Context): OpenWeatherMapApiService {
        return ApiUtil.buildRetrofit(
            ApiUtil.buildOpenWeatherOkHttpClient(context),
            "https://community-open-weather-map.p.rapidapi.com"
        ).create(OpenWeatherMapApiService::class.java)
    }
}