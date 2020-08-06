package com.example.workoutapp.domain.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutRepository {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>>

    fun deleteWorkout(workoutId: Long): Completable

    fun softDeleteWorkout(workoutId: Long): Completable

    fun undoSoftDeleteWorkout(workoutId: Long): Completable
}