package com.example.workoutnotebook.di.consent

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.consent.ConsentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ConsentActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun providesConsentActivity(): ConsentActivity
}