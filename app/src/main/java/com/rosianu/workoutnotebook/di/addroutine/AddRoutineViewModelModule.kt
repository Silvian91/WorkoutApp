package com.rosianu.workoutnotebook.di.addroutine

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.addroutine.AddRoutineViewModel
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