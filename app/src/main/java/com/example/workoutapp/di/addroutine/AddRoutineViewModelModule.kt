package com.example.workoutapp.di.addroutine

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.addroutine.AddRoutineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddRoutineViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddRoutineViewModel::class)
    fun bindsAddRoutineViewModel(viewModel: AddRoutineViewModel) : ViewModel

}