package com.rosianu.workoutnotebook.di.register

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RegisterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun bindsRegisterViewModel(viewModel: RegisterViewModel): ViewModel

}