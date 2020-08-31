package com.example.workoutapp.di.profile

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindsProfileViewModel(viewModel: ProfileViewModel): ViewModel

}