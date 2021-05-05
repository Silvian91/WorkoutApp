package com.rosianu.workoutnotebook.di.showroutine

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.showroutine.ShowRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowRoutineActivity(): ShowRoutineActivity

}