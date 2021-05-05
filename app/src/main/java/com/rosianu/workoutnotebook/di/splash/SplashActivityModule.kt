package com.rosianu.workoutnotebook.di.splash

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SplashActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideSplashActivity(): SplashActivity

}