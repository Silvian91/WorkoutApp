package com.example.workoutapp.di.home

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

}