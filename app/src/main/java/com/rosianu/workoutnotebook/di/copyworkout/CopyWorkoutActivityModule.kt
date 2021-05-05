package com.rosianu.workoutnotebook.di.copyworkout

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.copyworkout.CopyWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CopyWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideCopyWorkoutActivity(): CopyWorkoutActivity

}