package com.rosianu.workoutnotebook.domain.workout

import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutRepository {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>>

    fun getWorkoutTitle(workoutId: Long): Single<List<WorkoutModel>>

    fun deleteWorkout(workoutId: Long): Completable

    fun softDeleteWorkout(workoutId: Long): Completable

    fun undoSoftDeleteWorkout(workoutId: Long): Completable
}