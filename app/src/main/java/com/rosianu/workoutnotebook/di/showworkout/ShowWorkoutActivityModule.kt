package com.rosianu.workoutnotebook.di.showworkout

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.showworkout.ShowWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowWorkoutActivity(): ShowWorkoutActivity

}