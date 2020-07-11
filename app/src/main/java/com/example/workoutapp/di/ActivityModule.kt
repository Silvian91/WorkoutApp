package com.example.workoutapp.di

import com.example.workoutapp.di.addroutine.AddRoutineActivityModule
import dagger.Module

/**
 * This module includes all feature activity modules
 */

@Module(includes = [AddRoutineActivityModule::class])
interface ActivityModule