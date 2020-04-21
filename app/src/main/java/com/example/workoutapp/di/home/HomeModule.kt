package com.example.workoutapp.di.home

import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSource
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRemoteDataSourceImpl
import com.example.workoutapp.data.chucknorrisquote.ChuckNorrisQuoteRepositoryImpl
import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
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
        chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository
    ): HomeContract.Presenter {
        return HomePresenter(compositeDisposable, chuckNorrisQuoteRepository)
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