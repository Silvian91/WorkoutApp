package com.example.workoutapp.di.profile

import com.example.workoutapp.ui.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(profileFragment: ProfileFragment)
}