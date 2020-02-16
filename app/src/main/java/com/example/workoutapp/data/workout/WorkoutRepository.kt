package com.example.workoutapp.data.workout

import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutRepository {

    fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long>

    fun getAllWorkouts(): List<WorkoutEntity>

    fun deleteRoutine(workoutId: Long): Completable
}