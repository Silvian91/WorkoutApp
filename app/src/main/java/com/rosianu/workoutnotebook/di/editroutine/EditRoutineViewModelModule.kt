package com.rosianu.workoutnotebook.di.editroutine

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.editroutine.EditRoutineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EditRoutineViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditRoutineViewModel::class)
    fun bindsEditRoutineViewModel(viewModel: EditRoutineViewModel): ViewModel

}