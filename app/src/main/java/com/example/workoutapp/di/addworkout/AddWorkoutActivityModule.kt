package com.example.workoutapp.di.addworkout

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddWorkoutActivity(): AddWorkoutActivity

}