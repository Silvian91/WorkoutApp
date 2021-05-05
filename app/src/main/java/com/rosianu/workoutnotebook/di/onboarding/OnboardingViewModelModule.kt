package com.rosianu.workoutnotebook.di.onboarding

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.onboarding.OnboardingViewModel
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