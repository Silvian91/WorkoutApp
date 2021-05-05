package com.rosianu.workoutnotebook.di.editworkout

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.editworkout.EditWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface EditWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideEditWorkoutActivity(): EditWorkoutActivity

}