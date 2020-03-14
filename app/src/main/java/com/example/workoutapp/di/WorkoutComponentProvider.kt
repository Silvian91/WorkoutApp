package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineComponent
import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutComponent
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.workoutapp.di.main.MainComponent
import com.example.workoutapp.di.main.MainModule
import com.example.workoutapp.di.showroutine.ShowRoutineComponent
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutComponent
import com.example.workoutapp.di.showworkout.ShowWorkoutModule

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

}