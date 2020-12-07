package com.example.workoutnotebook.di.showworkout

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.showworkout.ShowWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowWorkoutActivity(): ShowWorkoutActivity

}