package com.rosianu.workoutnotebook.di.showroutine

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.showroutine.ShowRoutineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ShowRoutineViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShowRoutineViewModel::class)
    fun bindsShowRoutineViewModel(viewModel: ShowRoutineViewModel): ViewModel

}