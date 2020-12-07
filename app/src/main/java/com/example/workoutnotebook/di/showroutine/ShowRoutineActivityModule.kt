package com.example.workoutnotebook.di.showroutine

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.showroutine.ShowRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowRoutineActivity(): ShowRoutineActivity

}