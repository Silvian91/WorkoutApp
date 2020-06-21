package com.example.workoutapp.di.home

import com.example.workoutapp.data.inspirationalquote.QuoteRemoteDataSource
import com.example.workoutapp.data.inspirationalquote.QuoteRemoteDataSourceImpl
import com.example.workoutapp.data.inspirationalquote.QuoteRepositoryImpl
import com.example.workoutapp.data.openweathermap.OpenWeatherMapRemoteDataSource
import com.example.workoutapp.data.openweathermap.OpenWeatherMapRemoteDataSourceImpl
import com.example.workoutapp.data.openweathermap.WeatherRepositoryImpl
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCaseImpl
import com.example.workoutapp.domain.inspirationalquote.QuoteRepository
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCaseImpl
import com.example.workoutapp.domain.openweathermap.WeatherRepository
import com.example.workoutapp.http.inspirationalquote.InspirationalApiService
import com.example.workoutapp.http.openweathermap.OpenWeatherMapApiService
import com.example.workoutapp.ui.main.HomeContract
import com.example.workoutapp.ui.main.HomePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeModule {

    @Provides
    fun providesMainPresenter(
        compositeDisposable: CompositeDisposable,
        getWeatherUseCase: GetWeatherUseCase,
        getQuoteUseCase: GetQuoteUseCase
    ): HomeContract.Presenter {
        return HomePresenter(
            compositeDisposable,
            getWeatherUseCase,
            getQuoteUseCase
        )
    }

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