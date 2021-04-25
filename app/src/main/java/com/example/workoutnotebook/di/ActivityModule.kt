package com.example.workoutnotebook.di

import com.example.workoutnotebook.di.addroutine.AddRoutineActivityModule
import com.example.workoutnotebook.di.addworkout.AddWorkoutActivityModule
import com.example.workoutnotebook.di.consent.ConsentActivityModule
import com.example.workoutnotebook.di.copyworkout.CopyWorkoutActivityModule
import com.example.workoutnotebook.di.editroutine.EditRoutineActivityModule
import com.example.workoutnotebook.di.editworkout.EditWorkoutActivityModule
import com.example.workoutnotebook.di.login.LoginActivityModule
import com.example.workoutnotebook.di.main.MainActivityModule
import com.example.workoutnotebook.di.onboarding.OnboardingActivityModule
import com.example.workoutnotebook.di.register.RegisterActivityModule
import com.example.workoutnotebook.di.showroutine.ShowRoutineActivityModule
import com.example.workoutnotebook.di.showworkout.ShowWorkoutActivityModule
import com.example.workoutnotebook.di.splash.SplashActivityModule
import dagger.Module

/**
 * This module includes all feature activity modules
 */

@Module(
    includes = [
        AddRoutineActivityModule::class,
        AddWorkoutActivityModule::class,
        ConsentActivityModule::class,
        CopyWorkoutActivityModule::class,
        EditWorkoutActivityModule::class,
        EditRoutineActivityModule::class,
        LoginActivityModule::class,
        MainActivityModule::class,
        OnboardingActivityModule::class,
        RegisterActivityModule::class,
        ShowRoutineActivityModule::class,
        ShowWorkoutActivityModule::class,
        SplashActivityModule::class
    ]
)
interface ActivityModule