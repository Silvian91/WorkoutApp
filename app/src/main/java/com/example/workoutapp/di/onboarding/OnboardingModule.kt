package com.example.workoutapp.di.onboarding

import com.example.workoutapp.ui.onboarding.OnboardingContract
import com.example.workoutapp.ui.onboarding.OnboardingPresenter
import dagger.Module
import dagger.Provides

@Module
class StartModule {

    @Provides
    fun providesStartPresenter() : OnboardingContract.Presenter{
        return OnboardingPresenter()
    }
}