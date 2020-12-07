package com.example.workoutnotebook.di.login

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideLoginActivity(): LoginActivity

}