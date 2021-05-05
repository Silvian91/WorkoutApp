package com.rosianu.workoutnotebook.di.showworkout

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.showworkout.ShowWorkoutViewModel
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