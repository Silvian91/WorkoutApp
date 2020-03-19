package com.example.workoutapp.di.register

import com.example.workoutapp.ui.register.RegisterActivity
import dagger.Subcomponent

@Subcomponent(modules = [RegisterModule::class])
interface RegisterComponent {
    fun inject(registerActivity: RegisterActivity)
}