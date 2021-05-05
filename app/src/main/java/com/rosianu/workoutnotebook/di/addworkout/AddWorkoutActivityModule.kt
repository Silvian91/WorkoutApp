package com.rosianu.workoutnotebook.di.addworkout

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.addworkout.AddWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddWorkoutActivity(): AddWorkoutActivity

}