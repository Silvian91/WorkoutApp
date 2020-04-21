package com.example.workoutapp.di.home

import com.example.workoutapp.ui.main.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}