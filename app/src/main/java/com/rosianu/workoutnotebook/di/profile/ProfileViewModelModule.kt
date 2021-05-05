package com.rosianu.workoutnotebook.di.profile

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.profile.ProfileViewModel
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