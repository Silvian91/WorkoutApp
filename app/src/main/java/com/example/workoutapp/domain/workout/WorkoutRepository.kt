package com.example.workoutapp.domain.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutRepository {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(): Single<List<WorkoutModel>>

    fun deleteRoutine(workoutId: Long): Completable
}