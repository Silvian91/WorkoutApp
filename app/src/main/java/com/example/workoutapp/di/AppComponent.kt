package com.example.workoutapp.di

import android.app.Application
import com.example.workoutapp.di.addroutine.AddRoutineComponent
import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutComponent
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.workoutapp.di.api.ApiModule
import com.example.workoutapp.di.home.HomeComponent
import com.example.workoutapp.di.home.HomeModule
import com.example.workoutapp.di.login.LoginComponent
import com.example.workoutapp.di.login.LoginModule
import com.example.workoutapp.di.profile.ProfileComponent
import com.example.workoutapp.di.profile.ProfileModule
import com.example.workoutapp.di.register.RegisterComponent
import com.example.workoutapp.di.register.RegisterModule
import com.example.workoutapp.di.showroutine.ShowRoutineComponent
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutComponent
import com.example.workoutapp.di.showworkout.ShowWorkoutModule
import com.example.workoutapp.di.splash.SplashComponent
import com.example.workoutapp.di.splash.SplashModule
import com.example.workoutapp.di.start.StartComponent
import com.example.workoutapp.di.start.StartModule
import com.example.workoutapp.ui.WorkoutApplication
import dagger.BindsInstance
import dagger.Component

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
@Component(modules = [AndroidModule::class, CompositeDisposableModule::class, ApiModule::class])
interface AppComponent {
    fun inject(workoutApplication: WorkoutApplication)
    fun plus(addWorkoutModule: AddWorkoutModule): AddWorkoutComponent
    fun plus(showWorkoutModule: ShowWorkoutModule): ShowWorkoutComponent
    fun plus(addRoutineModule: AddRoutineModule): AddRoutineComponent
    fun plus(showRoutineModule: ShowRoutineModule): ShowRoutineComponent
    fun plus(homeModule: HomeModule): HomeComponent
    fun plus(loginModule: LoginModule): LoginComponent
    fun plus(registerModule: RegisterModule): RegisterComponent
    fun plus(profileModule: ProfileModule): ProfileComponent
    fun plus(splashModule: SplashModule): SplashComponent
    fun plus(startModule: StartModule): StartComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}