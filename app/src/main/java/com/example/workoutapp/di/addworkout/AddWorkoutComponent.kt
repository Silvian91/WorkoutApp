package com.example.workoutapp.di.addworkout

import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import dagger.Subcomponent

@Subcomponent(modules = [AddWorkoutModule::class])
interface AddWorkoutComponent {
    fun inject(addWorkoutActivity: AddWorkoutActivity)
}