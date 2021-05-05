package com.rosianu.workoutnotebook.di.home

import android.content.Context
import com.rosianu.workoutnotebook.data.inspirationalquote.QuoteRemoteDataSource
import com.rosianu.workoutnotebook.data.inspirationalquote.QuoteRemoteDataSourceImpl
import com.rosianu.workoutnotebook.data.inspirationalquote.QuoteRepositoryImpl
import com.rosianu.workoutnotebook.data.location.LocationDataSource
import com.rosianu.workoutnotebook.data.location.LocationDataSourceImpl
import com.rosianu.workoutnotebook.data.location.LocationRepositoryImpl
import com.rosianu.workoutnotebook.data.location.LocationProvider
import com.rosianu.workoutnotebook.data.openweathermap.OpenWeatherMapRemoteDataSource
import com.rosianu.workoutnotebook.data.openweathermap.OpenWeatherMapRemoteDataSourceImpl
import com.rosianu.workoutnotebook.data.openweathermap.WeatherRepositoryImpl
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCaseImpl
import com.rosianu.workoutnotebook.domain.inspirationalquote.QuoteRepository
import com.rosianu.workoutnotebook.domain.location.*
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCaseImpl
import com.rosianu.workoutnotebook.domain.weather.WeatherRepository
import com.rosianu.workoutnotebook.http.inspirationalquote.InspirationalApiService
import com.rosianu.workoutnotebook.http.openweathermap.OpenWeatherMapApiService
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