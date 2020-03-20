package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutLocalDataSource {

    fun insertWorkout(workoutModel: WorkoutModel): Single<Long>

    fun getAllWorkouts(): Single<ArrayList<WorkoutModel>>

    fun deleteRoutines(workoutId: Long): Completable
}