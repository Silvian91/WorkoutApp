package com.rosianu.workoutnotebook.di.addroutine

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.addroutine.AddRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddRoutineActivity() : AddRoutineActivity

}