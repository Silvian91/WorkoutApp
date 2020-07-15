package com.example.workoutapp.di.android

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.workoutapp.ui.WorkoutApplication
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {

    @Provides
    fun providesContext(application: WorkoutApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(WORKOUT_APP_SHARED_PREFERENCES, MODE_PRIVATE)
    }

    companion object {
        private const val WORKOUT_APP_SHARED_PREFERENCES = "WORKOUT_APP_SHARED_PREFERENCES"
    }

}