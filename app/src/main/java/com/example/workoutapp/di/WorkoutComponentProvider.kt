package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineComponent
import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutComponent
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.workoutapp.di.login.LoginComponent
import com.example.workoutapp.di.login.LoginModule
import com.example.workoutapp.di.main.MainComponent
import com.example.workoutapp.di.main.MainModule
import com.example.workoutapp.di.register.RegisterComponent
import com.example.workoutapp.di.register.RegisterModule
import com.example.workoutapp.di.showroutine.ShowRoutineComponent
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutComponent
import com.example.workoutapp.di.showworkout.ShowWorkoutModule
import com.example.workoutapp.di.signup.SignupComponent
import com.example.workoutapp.di.signup.SignupModule

class WorkoutComponentProvider(val appComponent: AppComponent) {

    fun createAddRoutineComponent(): AddRoutineComponent{
        return appComponent.plus(AddRoutineModule())
    }

    fun createAddWorkoutComponent(): AddWorkoutComponent{
        return appComponent.plus(AddWorkoutModule())
    }

    fun createShowWorkoutComponent(): ShowWorkoutComponent{
        return appComponent.plus(ShowWorkoutModule())
    }

    fun createShowRoutineComponent(): ShowRoutineComponent{
        return appComponent.plus(ShowRoutineModule())
    }

    fun createMainComponent(): MainComponent{
        return appComponent.plus(MainModule())
    }

    fun createSignupComponent(): SignupComponent{
        return appComponent.plus(SignupModule())
    }

    fun createLoginComponent(): LoginComponent{
        return appComponent.plus(LoginModule())
    }

    fun createRegisterComponent(): RegisterComponent{
        return appComponent.plus(RegisterModule())
    }

}