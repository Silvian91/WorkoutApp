package com.example.workoutapp.di.addroutine

import com.example.workoutapp.addroutine.AddRoutineActivity
import dagger.Subcomponent

@Subcomponent(modules = [AddRoutineModule::class])
interface AddRoutineComponent {
    fun inject(addRoutineActivity: AddRoutineActivity)
}