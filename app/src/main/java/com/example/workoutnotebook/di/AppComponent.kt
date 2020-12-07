package com.example.workoutnotebook.di

import com.example.workoutnotebook.di.addroutine.AddRoutineModule
import com.example.workoutnotebook.di.addworkout.AddWorkoutModule
import com.example.lib_key_value_storage.di.android.SharedPreferencesModule
import com.example.workoutnotebook.di.api.ApiModule
import com.example.workoutnotebook.di.app.AppModule
import com.example.workoutnotebook.di.home.HomeModule
import com.example.workoutnotebook.di.login.LoginModule
import com.example.workoutnotebook.di.profile.ProfileModule
import com.example.workoutnotebook.di.register.RegisterModule
import com.example.workoutnotebook.di.showroutine.ShowRoutineModule
import com.example.workoutnotebook.di.showworkout.ShowWorkoutModule
import com.example.workoutnotebook.di.splash.SplashModule
import com.example.workoutnotebook.ui.WorkoutApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        com.example.core.di.CompositeDisposableModule::class,
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