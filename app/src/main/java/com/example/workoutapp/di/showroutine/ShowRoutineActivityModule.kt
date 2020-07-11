package com.example.workoutapp.di.showroutine

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.showroutine.ShowRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowRoutineActivity(): ShowRoutineActivity

}