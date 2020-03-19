package com.example.workoutapp.di.main

import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSource
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSourceImpl
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRepositoryImpl
import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
import com.example.workoutapp.ui.main.MainContract
import com.example.workoutapp.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainModule {

    @Provides
    fun providesMainPresenter(
        compositeDisposable: CompositeDisposable,
        chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository
    ): MainContract.Presenter {
        return MainPresenter(compositeDisposable, chuckNorrisQuoteRepository)
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