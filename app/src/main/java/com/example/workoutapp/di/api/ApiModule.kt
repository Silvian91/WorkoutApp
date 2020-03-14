package com.example.workoutapp.di.api

import android.content.Context
import com.example.workoutapp.http.ApiUtil
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
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
}