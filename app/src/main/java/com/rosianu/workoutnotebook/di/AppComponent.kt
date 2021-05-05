package com.rosianu.workoutnotebook.di

import com.rosianu.workoutnotebook.di.addroutine.AddRoutineModule
import com.rosianu.workoutnotebook.di.addworkout.AddWorkoutModule
import com.rosianu.lib_key_value_storage.di.android.SharedPreferencesModule
import com.rosianu.workoutnotebook.di.api.ApiModule
import com.rosianu.workoutnotebook.di.app.AppModule
import com.rosianu.workoutnotebook.di.home.HomeModule
import com.rosianu.workoutnotebook.di.login.LoginModule
import com.rosianu.workoutnotebook.di.profile.ProfileModule
import com.rosianu.workoutnotebook.di.register.RegisterModule
import com.rosianu.workoutnotebook.di.showroutine.ShowRoutineModule
import com.rosianu.workoutnotebook.di.showworkout.ShowWorkoutModule
import com.rosianu.workoutnotebook.di.splash.SplashModule
import com.rosianu.workoutnotebook.ui.WorkoutApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        com.rosianu.core.di.CompositeDisposableModule::class,
        AddRoutineModule::class,
        AddWorkoutModule::class,
        ApiModule::class,
        HomeModule::class,
        LoginModule::class,
        ProfileModule::class,
        RegisterModule::class,
        SharedPreferencesModule::class,
        ShowRoutineModule::class,
        ShowWorkoutModule::class,
        SplashModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<WorkoutApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<WorkoutApplication>

}