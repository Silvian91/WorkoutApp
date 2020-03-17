package com.example.workoutapp.di.main

import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSource
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSourceImpl
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRepositoryImpl
import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
import com.example.workoutapp.ui.mainactivity.MainContract
import com.example.workoutapp.ui.mainactivity.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainModule {

    @Provides
    fun providesMainPresenter(
        chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository,
        compositeDisposable: CompositeDisposable
    ): MainContract.Presenter {
        return MainPresenter(chuckNorrisQuoteRepository, compositeDisposable)
    }

    @Provides
    fun providesChuckNorrisRepository(
        chuckNorrisQuoteRemoteDataSource: ChuckNorrisQuoteRemoteDataSource
    ): ChuckNorrisQuoteRepository {
        return ChuckNorrisQuoteRepositoryImpl(chuckNorrisQuoteRemoteDataSource)
    }

    @Provides
    fun providesChuckNorrisDataSource(
        chuckNorrisApiService: ChuckNorrisApiService
    ): ChuckNorrisQuoteRemoteDataSource {
        return ChuckNorrisQuoteRemoteDataSourceImpl(chuckNorrisApiService)
    }

}