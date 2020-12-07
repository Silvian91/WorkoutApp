package com.example.workoutnotebook.di.main

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentsViewModelModule::class])
    fun provideMainActivity(): MainActivity

}