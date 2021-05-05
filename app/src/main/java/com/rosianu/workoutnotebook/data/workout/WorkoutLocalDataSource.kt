package com.rosianu.workoutnotebook.data.workout

import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutLocalDataSource {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>>

    fun getWorkoutTitle(workoutId: Long): Single<List<WorkoutModel>>

    fun deleteWorkout(workoutId: Long): Completable

    fun softDeleteWorkout(workoutId: Long): Completable

    fun undoSoftDeleteWorkout(workoutId: Long): Completable

}