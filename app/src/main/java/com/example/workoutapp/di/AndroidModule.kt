package com.example.workoutapp.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    @Provides
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(WORKOUT_APP_SHARED_PREFERENCES, MODE_PRIVATE)
    }

    companion object {
        private const val WORKOUT_APP_SHARED_PREFERENCES = "WORKOUT_APP_SHARED_PREFERENCES"
    }

}