package com.example.workoutapp.di.register

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.register.RegisterViewModel
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