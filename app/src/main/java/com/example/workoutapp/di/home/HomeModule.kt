package com.example.workoutapp.di.home

import com.example.workoutapp.data.inspirationalquote.InspirationalQuoteRemoteDataSource
import com.example.workoutapp.data.inspirationalquote.InspirationalQuoteRemoteDataSourceImpl
import com.example.workoutapp.data.inspirationalquote.InspirationalQuoteRepositoryImpl
import com.example.workoutapp.data.openweathermap.OpenWeatherMapRemoteDataSource
import com.example.workoutapp.data.openweathermap.OpenWeatherMapRemoteDataSourceImpl
import com.example.workoutapp.data.openweathermap.OpenWeatherMapRepositoryImpl
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCaseImpl
import com.example.workoutapp.domain.inspirationalquote.InspirationalQuoteRepository
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCaseImpl
import com.example.workoutapp.domain.openweathermap.OpenWeatherMapRepository
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
    fun providesGetQuoteUseCase(inspirationalQuoteRepository: InspirationalQuoteRepository): GetQuoteUseCase {
        return GetQuoteUseCaseImpl(inspirationalQuoteRepository)
    }

    @Provides
    fun providesGetWeatherUseCase(
        openWeatherMapRepository: OpenWeatherMapRepository
    ) : GetWeatherUseCase {
        return GetWeatherUseCaseImpl(openWeatherMapRepository)
    }

    @Provides
    fun providesInspirationalQuoteRepository(
        inspirationalQuoteRemoteDataSource: InspirationalQuoteRemoteDataSource
    ): InspirationalQuoteRepository {
        return InspirationalQuoteRepositoryImpl(inspirationalQuoteRemoteDataSource)
    }

    @Provides
    fun providesInspirationalQuoteRemoteDataSource(
        inspirationalApiService: InspirationalApiService
    ): InspirationalQuoteRemoteDataSource {
        return InspirationalQuoteRemoteDataSourceImpl(inspirationalApiService)
    }

    @Provides
    fun providesOpenWeatherMapRepository(
        openWeatherMapRemoteDataSource: OpenWeatherMapRemoteDataSource
    ): OpenWeatherMapRepository {
        return OpenWeatherMapRepositoryImpl(openWeatherMapRemoteDataSource)
    }

    @Provides
    fun providesOpenWeatherMapRemoteDataSource(
        openWeatherMapApiService: OpenWeatherMapApiService
    ): OpenWeatherMapRemoteDataSource {
        return OpenWeatherMapRemoteDataSourceImpl(openWeatherMapApiService)
    }

}