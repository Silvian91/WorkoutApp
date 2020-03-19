package com.example.workoutapp.di.main

import com.example.workoutapp.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}