package com.example.workoutapp.di.showroutine

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.showroutine.ShowRoutineViewModel
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