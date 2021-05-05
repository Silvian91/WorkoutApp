package com.rosianu.core.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class CompositeDisposableModule {
    @Provides
    fun providesCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}