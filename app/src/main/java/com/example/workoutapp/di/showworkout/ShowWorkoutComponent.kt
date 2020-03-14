package com.example.workoutapp.di.showworkout

import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import dagger.Subcomponent

@Subcomponent(modules = [ShowWorkoutModule::class])
interface ShowWorkoutComponent {
    fun inject(showWorkoutActivity: ShowWorkoutActivity)
}