package com.example.workoutapp.di.addroutine

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.addroutine.AddRoutineActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddRoutineActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideAddRoutineActivity() : AddRoutineActivity

}