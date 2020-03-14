package com.example.workoutapp.di.showroutine

import com.example.workoutapp.ui.showroutine.ShowRoutineActivity
import dagger.Subcomponent

@Subcomponent(modules = [ShowRoutineModule::class])
interface ShowRoutineComponent {
    fun inject(showRoutineActivity: ShowRoutineActivity)
}