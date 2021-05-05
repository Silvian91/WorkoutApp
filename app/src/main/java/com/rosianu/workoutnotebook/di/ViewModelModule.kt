package com.rosianu.workoutnotebook.di

import androidx.lifecycle.ViewModelProvider
import com.rosianu.workoutnotebook.di.addroutine.AddRoutineViewModelModule
import com.rosianu.workoutnotebook.di.addworkout.AddWorkoutViewModelModule
import com.rosianu.workoutnotebook.di.consent.ConsentViewModelModule
import com.rosianu.workoutnotebook.di.copyworkout.CopyWorkoutViewModelModule
import com.rosianu.workoutnotebook.di.editroutine.EditRoutineViewModelModule
import com.rosianu.workoutnotebook.di.editworkout.EditWorkoutViewModelModule
import com.rosianu.workoutnotebook.di.home.HomeViewModelModule
import com.rosianu.workoutnotebook.di.login.LoginViewModelModule
import com.rosianu.workoutnotebook.di.onboarding.OnboardingViewModelModule
import com.rosianu.workoutnotebook.di.profile.ProfileViewModelModule
import com.rosianu.workoutnotebook.di.register.RegisterViewModelModule
import com.rosianu.workoutnotebook.di.showroutine.ShowRoutineViewModelModule
import com.rosianu.workoutnotebook.di.showworkout.ShowWorkoutViewModelModule
import com.rosianu.workoutnotebook.di.splash.SplashViewModelModule
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
        EditWorkoutViewModelModule::class,
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
    fun bindViewModelFactory(factory: com.rosianu.core.di.DaggerViewModelFactory): ViewModelProvider.Factory

}