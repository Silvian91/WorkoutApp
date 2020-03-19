package com.example.workoutapp.di.login

import com.example.workoutapp.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}