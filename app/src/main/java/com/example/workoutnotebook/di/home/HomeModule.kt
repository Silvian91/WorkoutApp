package com.example.workoutnotebook.di.home

import android.content.Context
import com.example.workoutnotebook.data.inspirationalquote.QuoteRemoteDataSource
import com.example.workoutnotebook.data.inspirationalquote.QuoteRemoteDataSourceImpl
import com.example.workoutnotebook.data.inspirationalquote.QuoteRepositoryImpl
import com.example.workoutnotebook.data.location.LocationDataSource
import com.example.workoutnotebook.data.location.LocationDataSourceImpl
import com.example.workoutnotebook.data.location.LocationRepositoryImpl
import com.example.workoutnotebook.data.location.LocationProvider
import com.example.workoutnotebook.data.openweathermap.OpenWeatherMapRemoteDataSource
import com.example.workoutnotebook.data.openweathermap.OpenWeatherMapRemoteDataSourceImpl
import com.example.workoutnotebook.data.openweathermap.WeatherRepositoryImpl
import com.example.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutnotebook.domain.inspirationalquote.GetQuoteUseCaseImpl
import com.example.workoutnotebook.domain.inspirationalquote.QuoteRepository
import com.example.workoutnotebook.domain.location.*
import com.example.workoutnotebook.domain.weather.GetWeatherUseCase
import com.example.workoutnotebook.domain.weather.GetWeatherUseCaseImpl
import com.example.workoutnotebook.domain.weather.WeatherRepository
import com.example.workoutnotebook.http.inspirationalquote.InspirationalApiService
import com.example.workoutnotebook.http.openweathermap.OpenWeatherMapApiService
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun providesGetQuoteUseCase(quoteRepository: QuoteRepository): GetQuoteUseCase {
        return GetQuoteUseCaseImpl(quoteRepository)
    }

    @Provides
    fun providesGetWeatherUseCase(
        weatherRepository: WeatherRepository
    ): GetWeatherUseCase {
        return GetWeatherUseCaseImpl(weatherRepository)
    }

    @Provides
    fun providesGetLocationUseCase(
        locationRepository: LocationRepository
    ): GetLocationUseCase {
        return GetLocationUseCaseImpl(locationRepository)
    }

    @Provides
    fun providesLocationRepository(
        locationDataSource: LocationDataSource
    ): LocationRepository {
        return LocationRepositoryImpl(locationDataSource)
    }

    @Provides
    fun providesLocationDataSource(
        context: Context
    ): LocationDataSource {
        return LocationDataSourceImpl(context)
    }

    @Provides
    fun providesUserLocation(
        context: Context
    ): LocationProvider {
        return LocationProvider(context)
    }

    @Provides
    fun providesInspirationalQuoteRepository(
        quoteRemoteDataSource: QuoteRemoteDataSource
    ): QuoteRepository {
        return QuoteRepositoryImpl(quoteRemoteDataSource)
    }

    @Provides
    fun providesInspirationalQuoteRemoteDataSource(
        inspirationalApiService: InspirationalApiService
    ): QuoteRemoteDataSource {
        return QuoteRemoteDataSourceImpl(inspirationalApiService)
    }

    @Provides
    fun providesOpenWeatherMapRepository(
        openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(openWeatherMapRemoteDataSource)
    }

    @Provides
    fun providesOpenWeatherMapRemoteDataSource(
        openWeatherMapApiService: OpenWeatherMapApiService
    ): OpenWeatherMapRemoteDataSource {
        return OpenWeatherMapRemoteDataSourceImpl(openWeatherMapApiService)
    }

}