package com.example.workoutapp.di.consent

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.ui.consent.ConsentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ConsentActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun providesConsentActivity(): ConsentActivity
}