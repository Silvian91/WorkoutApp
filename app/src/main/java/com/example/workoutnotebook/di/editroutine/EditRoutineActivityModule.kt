package com.example.workoutnotebook.di.editroutine

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.editroutine.EditRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface EditRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideEditRoutineActivity(): EditRoutineActivity

}