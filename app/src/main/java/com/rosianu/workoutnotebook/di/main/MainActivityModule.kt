package com.rosianu.workoutnotebook.di.main

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentsViewModelModule::class])
    fun provideMainActivity(): MainActivity

}