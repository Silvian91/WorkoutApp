package com.rosianu.workoutnotebook.di.login

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideLoginActivity(): LoginActivity

}