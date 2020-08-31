package com.example.workoutapp.di.showworkout

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.showworkout.ShowWorkoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ShowWorkoutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShowWorkoutViewModel::class)
    fun bindsShowWorkoutViewModel(viewModel: ShowWorkoutViewModel): ViewModel

}