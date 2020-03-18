package com.example.workoutapp.di.login

import com.example.workoutapp.ui.loginactivity.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}