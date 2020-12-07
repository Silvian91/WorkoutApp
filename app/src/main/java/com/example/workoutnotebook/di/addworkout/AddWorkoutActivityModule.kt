package com.example.workoutnotebook.di.addworkout

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.addworkout.AddWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddWorkoutActivity(): AddWorkoutActivity

}