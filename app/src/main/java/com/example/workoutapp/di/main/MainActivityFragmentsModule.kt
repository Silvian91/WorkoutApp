package com.example.workoutapp.di.main

import com.example.workoutapp.di.FragmentScope
import com.example.workoutapp.ui.home.HomeFragment
import com.example.workoutapp.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

}