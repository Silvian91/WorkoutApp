package com.rosianu.workoutnotebook.di.addworkout

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.addworkout.AddWorkoutViewModel
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