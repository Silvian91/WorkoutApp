package com.example.workoutapp.data.workout

import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutLocalDataSource {

    fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long>

    fun getAllWorkouts(): List<WorkoutEntity>

    fun deleteRoutines(workoutId: Long): Completable
}