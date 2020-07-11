package com.example.workoutapp.di.login

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideLoginActivity(): LoginActivity

}