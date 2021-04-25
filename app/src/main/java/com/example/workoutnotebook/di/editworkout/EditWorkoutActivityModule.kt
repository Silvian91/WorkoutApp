package com.example.workoutnotebook.di.editworkout

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.editworkout.EditWorkoutActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface EditWorkoutActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideEditWorkoutActivity(): EditWorkoutActivity

}