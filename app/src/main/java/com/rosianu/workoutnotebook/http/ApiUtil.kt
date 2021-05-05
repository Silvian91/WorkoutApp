package com.rosianu.workoutnotebook.http

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rosianu.workoutnotebook.http.Constants.ACCEPT_VALUE
import com.rosianu.workoutnotebook.http.Constants.HEADER_ACCEPT
import com.rosianu.workoutnotebook.http.Constants.HEADER_HOST
import com.rosianu.workoutnotebook.http.Constants.HEADER_KEY
import com.rosianu.workoutnotebook.http.Constants.HOST_QUOTE_VALUE
import com.rosianu.workoutnotebook.http.Constants.HOST_WEATHER_VALUE
import com.rosianu.workoutnotebook.http.Constants.KEY_VALUE
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
                    .header(HEADER_HOST, HOST_WEATHER_VALUE)
                    .header(HEADER_KEY, KEY_VALUE)
                    .header(HEADER_ACCEPT, ACCEPT_VALUE)
                chain.proceed(headerBuilder.build())
            }
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    fun buildInspirationalQuotesOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val headerBuilder = request.newBuilder()
                    .header(HEADER_HOST, HOST_QUOTE_VALUE)
                    .header(HEADER_KEY, KEY_VALUE)
                    .header(HEADER_ACCEPT, ACCEPT_VALUE)
                chain.proceed(headerBuilder.build())
            }
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }
}