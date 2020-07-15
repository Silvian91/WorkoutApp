package com.example.workoutapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.workoutapp.di.splash.SplashViewModelModule
import dagger.Binds
import dagger.Module

/**
 * This module includes all feature view holder modules
 */

@Module(includes = [SplashViewModelModule::class])
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}