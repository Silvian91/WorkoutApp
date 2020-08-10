package com.example.workoutapp.di.main

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentsViewModelModule::class])
    fun provideMainActivity(): MainActivity

}