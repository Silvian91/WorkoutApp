package com.rosianu.workoutnotebook.di.editroutine

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.editroutine.EditRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface EditRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun providesEditRoutineActivity(): EditRoutineActivity

}