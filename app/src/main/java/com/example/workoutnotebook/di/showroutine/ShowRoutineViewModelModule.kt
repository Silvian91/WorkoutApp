package com.example.workoutnotebook.di.showroutine

import androidx.lifecycle.ViewModel
import com.example.workoutnotebook.di.ViewModelKey
import com.example.workoutnotebook.ui.showroutine.ShowRoutineViewModel
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