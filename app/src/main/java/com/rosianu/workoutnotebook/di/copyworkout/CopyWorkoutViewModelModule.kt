package com.rosianu.workoutnotebook.di.copyworkout

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.copyworkout.CopyWorkoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CopyWorkoutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CopyWorkoutViewModel::class)
    fun bindsCopyWorkoutViewModel(viewModel: CopyWorkoutViewModel): ViewModel

}