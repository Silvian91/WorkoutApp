package com.example.workoutnotebook.di.addworkout

import androidx.lifecycle.ViewModel
import com.example.workoutnotebook.di.ViewModelKey
import com.example.workoutnotebook.ui.addworkout.AddWorkoutViewModel
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