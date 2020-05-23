package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

interface WorkoutLocalDataSource {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>>

    fun deleteWorkout(workoutId: Long): Completable
}