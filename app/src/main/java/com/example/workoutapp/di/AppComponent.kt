package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.workoutapp.di.android.AndroidModule
import com.example.workoutapp.di.api.ApiModule
import com.example.workoutapp.di.home.HomeModule
import com.example.workoutapp.di.login.LoginModule
import com.example.workoutapp.di.onboarding.OnboardingModule
import com.example.workoutapp.di.profile.ProfileModule
import com.example.workoutapp.di.register.RegisterModule
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutModule
import com.example.workoutapp.di.splash.SplashModule
import com.example.workoutapp.ui.WorkoutApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * if you wanna make a new feature:
 *
 *
 * - make a new component interface to inject to your new activities and fragments
 * - make a new module to describe how to create the dependencies in your activities and fragments
 *
 *
 * - add a plus method to this app component
 * - add a create method to FlashCardComponentProvider
 *
 *
 * the rest was groundwork that makes it easier to work with Dagger
 */
@Singleton
@Component(
    modules = [AndroidModule::class,
        CompositeDisposableModule::class,
        ApiModule::class,
        AddRoutineModule::class,
        AddWorkoutModule::class,
        ApiModule::class,
        HomeModule::class,
        LoginModule::class,
        ProfileModule::class,
        RegisterModule::class,
        ShowRoutineModule::class,
        ShowWorkoutModule::class,
        SplashModule::class,
        OnboardingModule::class,
        AndroidModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<WorkoutApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<WorkoutApplication>

}