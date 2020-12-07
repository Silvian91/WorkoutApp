package com.example.workoutnotebook.di.showworkout

import androidx.lifecycle.ViewModel
import com.example.workoutnotebook.di.ViewModelKey
import com.example.workoutnotebook.ui.showworkout.ShowWorkoutViewModel
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