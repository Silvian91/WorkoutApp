package com.example.workoutapp.di.addworkout

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.addworkout.AddWorkoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddWorkoutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddWorkoutViewModel::class)
    fun bindsAddWorkoutViewModel(viewModel: AddWorkoutViewModel): ViewModel

}