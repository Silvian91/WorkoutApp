package com.example.workoutapp

import android.app.Application
import com.example.workoutapp.di.WorkoutAppComponentBuilder
import com.example.workoutapp.di.WorkoutComponentProvider

class WorkoutApplication: Application() {
    lateinit var components: WorkoutComponentProvider

    override fun onCreate() {
        super.onCreate()
        application = this
        components = WorkoutComponentProvider(WorkoutAppComponentBuilder().build(this))
    }

    companion object {
        private lateinit var application: WorkoutApplication

        fun get(): WorkoutApplication {
            return application
        }
    }
}