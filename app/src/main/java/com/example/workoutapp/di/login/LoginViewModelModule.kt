package com.example.workoutapp.di.login

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindsLoginViewModel(viewModel: LoginViewModel): ViewModel

}