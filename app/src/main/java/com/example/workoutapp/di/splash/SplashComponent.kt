package com.example.workoutapp.di.splash

import com.example.workoutapp.ui.splash.SplashActivity
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}