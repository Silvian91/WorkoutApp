package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.lib_key_value_storage.di.android.SharedPreferencesModule
import com.example.workoutapp.di.api.ApiModule
import com.example.workoutapp.di.app.AppModule
import com.example.workoutapp.di.home.HomeModule
import com.example.workoutapp.di.login.LoginModule
import com.example.workoutapp.di.profile.ProfileModule
import com.example.workoutapp.di.register.RegisterModule
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutModule
import com.example.workoutapp.di.splash.SplashModule
import com.example.workoutapp.ui.WorkoutApplication
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