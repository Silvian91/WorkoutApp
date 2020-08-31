package com.example.workoutapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.workoutapp.di.addroutine.AddRoutineViewModelModule
import com.example.workoutapp.di.addworkout.AddWorkoutViewModelModule
import com.example.workoutapp.di.home.HomeViewModelModule
import com.example.workoutapp.di.login.LoginViewModelModule
import com.example.workoutapp.di.onboarding.OnboardingViewModelModule
import com.example.workoutapp.di.profile.ProfileViewModelModule
import com.example.workoutapp.di.register.RegisterViewModelModule
import com.example.workoutapp.di.showroutine.ShowRoutineViewModelModule
import com.example.workoutapp.di.showworkout.ShowWorkoutViewModelModule
import com.example.workoutapp.di.splash.SplashViewModelModule
import dagger.Binds
import dagger.Module

/**
 * This module includes all feature view holder modules
 */

@Module(
    includes = [
        AddRoutineViewModelModule::class,
        AddWorkoutViewModelModule::class,
        HomeViewModelModule::class,
        LoginViewModelModule::class,
        OnboardingViewModelModule::class,
        ProfileViewModelModule::class,
        RegisterViewModelModule::class,
        ShowRoutineViewModelModule::class,
        ShowWorkoutViewModelModule::class,
        SplashViewModelModule::class
    ]
)
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}