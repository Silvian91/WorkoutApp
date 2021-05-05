package com.rosianu.workoutnotebook.di.app

import android.content.Context
import com.rosianu.workoutnotebook.ui.WorkoutApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: WorkoutApplication): Context {
        return application.applicationContext
    }

}