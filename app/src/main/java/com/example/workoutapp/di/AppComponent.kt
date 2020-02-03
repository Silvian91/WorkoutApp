package com.example.workoutapp.di

import android.app.Application
import com.example.workoutapp.WorkoutApplication
import com.example.workoutapp.di.addroutine.AddRoutineComponent
import com.example.workoutapp.di.addroutine.AddRoutineModule
import com.example.workoutapp.di.addworkout.AddWorkoutComponent
import com.example.workoutapp.di.addworkout.AddWorkoutModule
import com.example.workoutapp.di.showroutine.ShowRoutineComponent
import com.example.workoutapp.di.showroutine.ShowRoutineModule
import com.example.workoutapp.di.showworkout.ShowWorkoutComponent
import com.example.workoutapp.di.showworkout.ShowWorkoutModule
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
@Component(modules = [AndroidModule::class, CompositeDisposableModule::class])
interface AppComponent {
    fun inject(workoutApplication: WorkoutApplication?)
    operator fun plus(addWorkoutModule: AddWorkoutModule?): AddWorkoutComponent?
    operator fun plus(showWorkoutModule: ShowWorkoutModule?): ShowWorkoutComponent?
    operator fun plus(addRoutineModule: AddRoutineModule?): AddRoutineComponent?
    operator fun plus(showRoutineModule: ShowRoutineModule?): ShowRoutineComponent?
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?

        fun build(): AppComponent?
    }
}