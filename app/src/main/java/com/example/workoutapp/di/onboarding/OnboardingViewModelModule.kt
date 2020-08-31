package com.example.workoutapp.di.onboarding

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.onboarding.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface OnboardingViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    fun bindsOnboardingViewModel(viewModel: OnboardingViewModel): ViewModel

}