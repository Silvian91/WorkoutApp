package com.example.workoutapp.http

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtil {

    fun buildRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun buildOpenWeatherOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val headerBuilder = request.newBuilder()
                    .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com/weather")
                    .header("x-rapidapi-key", "9fc5dc8d71msh5cfb93a2eb45d4ep1726e8jsn4423db34d1ba")
                    .header("accept", "application/json")
                chain.proceed(headerBuilder.build())
            }
            .addInterceptor(ChuckInterceptor(context))
            .build()
    }

    fun buildChuckNorrisOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val headerBuilder = request.newBuilder()
                    .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                    .header("x-rapidapi-key", "9fc5dc8d71msh5cfb93a2eb45d4ep1726e8jsn4423db34d1ba")
                    .header("accept", "application/json")
                chain.proceed(headerBuilder.build())
            }
            .addInterceptor(ChuckInterceptor(context))
            .build()
    }
}