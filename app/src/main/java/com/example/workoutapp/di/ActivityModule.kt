package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineActivityModule
import com.example.workoutapp.di.addworkout.AddWorkoutActivityModule
import com.example.workoutapp.di.login.LoginActivityModule
import com.example.workoutapp.di.main.MainActivityModule
import com.example.workoutapp.di.onboarding.OnboardingActivityModule
import com.example.workoutapp.di.register.RegisterActivityModule
import com.example.workoutapp.di.showroutine.ShowRoutineActivityModule
import com.example.workoutapp.di.showworkout.ShowWorkoutActivityModule
import com.example.workoutapp.di.splash.SplashActivityModule
import dagger.Module

/**
 * This module includes all feature activity modules
 */

@Module(
    includes = [
        AddRoutineActivityModule::class,
        AddWorkoutActivityModule::class,
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