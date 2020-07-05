package com.example.workoutapp.di.start

import com.example.workoutapp.di.splash.SplashModule
import com.example.workoutapp.ui.start.StartActivity
import dagger.Subcomponent

@Subcomponent(modules = [StartModule::class])
interface StartComponent {
    fun inject(startActivity: StartActivity)
}