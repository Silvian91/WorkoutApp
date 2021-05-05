package com.rosianu.workoutnotebook.di

import com.rosianu.workoutnotebook.di.addroutine.AddRoutineActivityModule
import com.rosianu.workoutnotebook.di.addworkout.AddWorkoutActivityModule
import com.rosianu.workoutnotebook.di.consent.ConsentActivityModule
import com.rosianu.workoutnotebook.di.copyworkout.CopyWorkoutActivityModule
import com.rosianu.workoutnotebook.di.editroutine.EditRoutineActivityModule
import com.rosianu.workoutnotebook.di.editworkout.EditWorkoutActivityModule
import com.rosianu.workoutnotebook.di.login.LoginActivityModule
import com.rosianu.workoutnotebook.di.main.MainActivityModule
import com.rosianu.workoutnotebook.di.onboarding.OnboardingActivityModule
import com.rosianu.workoutnotebook.di.register.RegisterActivityModule
import com.rosianu.workoutnotebook.di.showroutine.ShowRoutineActivityModule
import com.rosianu.workoutnotebook.di.showworkout.ShowWorkoutActivityModule
import com.rosianu.workoutnotebook.di.splash.SplashActivityModule
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