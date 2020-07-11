package com.example.workoutapp.di.splash

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SplashActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideSplashActivity(): SplashActivity

}