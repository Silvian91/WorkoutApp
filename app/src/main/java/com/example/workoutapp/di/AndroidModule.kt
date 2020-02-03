package com.example.workoutapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    @Provides
    fun providesContext(application: Application): Context {
        return application
    }
}