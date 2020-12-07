package com.example.workoutnotebook.di.splash

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SplashActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideSplashActivity(): SplashActivity

}