package com.example.workoutapp.di.register

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RegisterActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideRegisterActivity(): RegisterActivity

}