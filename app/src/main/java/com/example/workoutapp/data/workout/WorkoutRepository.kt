package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(): Single<ArrayList<WorkoutModel>>

    fun deleteRoutine(workoutId: Long): Completable
}