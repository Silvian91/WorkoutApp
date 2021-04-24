package com.example.workoutnotebook.di

import androidx.lifecycle.ViewModelProvider
import com.example.workoutnotebook.di.addroutine.AddRoutineViewModelModule
import com.example.workoutnotebook.di.addworkout.AddWorkoutViewModelModule
import com.example.workoutnotebook.di.consent.ConsentViewModelModule
import com.example.workoutnotebook.di.copyworkout.CopyWorkoutViewModelModule
import com.example.workoutnotebook.di.editroutine.EditRoutineViewModelModule
import com.example.workoutnotebook.di.home.HomeViewModelModule
import com.example.workoutnotebook.di.login.LoginViewModelModule
import com.example.workoutnotebook.di.onboarding.OnboardingViewModelModule
import com.example.workoutnotebook.di.profile.ProfileViewModelModule
import com.example.workoutnotebook.di.register.RegisterViewModelModule
import com.example.workoutnotebook.di.showroutine.ShowRoutineViewModelModule
import com.example.workoutnotebook.di.showworkout.ShowWorkoutViewModelModule
import com.example.workoutnotebook.di.splash.SplashViewModelModule
import dagger.Binds
import dagger.Module

/**
 * This module includes all feature view_holder_profile_routines_count holder modules
 */

@Module(
    includes = [
        AddRoutineViewModelModule::class,
        AddWorkoutViewModelModule::class,
        ConsentViewModelModule::class,
        CopyWorkoutViewModelModule::class,
        EditRoutineViewModelModule::class,
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
    fun bindViewModelFactory(factory: com.example.core.di.DaggerViewModelFactory): ViewModelProvider.Factory

}