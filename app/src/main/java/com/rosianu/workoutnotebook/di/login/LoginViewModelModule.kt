package com.rosianu.workoutnotebook.di.login

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.login.LoginViewModel
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