package com.example.workoutnotebook.di.addroutine

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.addroutine.AddRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddRoutineActivity() : AddRoutineActivity

}