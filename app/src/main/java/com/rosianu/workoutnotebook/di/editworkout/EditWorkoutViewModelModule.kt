package com.rosianu.workoutnotebook.di.editworkout

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.editworkout.EditWorkoutViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditWorkoutViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditWorkoutViewModel::class)
    fun bindsEditWorkoutViewModel(viewModel: EditWorkoutViewModel): ViewModel

}