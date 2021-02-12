package com.example.workoutnotebook.di.copyworkout

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.copyworkout.CopyWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CopyWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideCopyWorkoutActivity(): CopyWorkoutActivity

}