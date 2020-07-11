package com.example.workoutapp.di.showworkout

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ShowWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideShowWorkoutActivity(): ShowWorkoutActivity

}