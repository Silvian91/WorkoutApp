package com.rosianu.workoutnotebook.di.splash

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}