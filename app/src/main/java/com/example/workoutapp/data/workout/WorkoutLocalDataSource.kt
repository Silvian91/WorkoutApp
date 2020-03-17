package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutLocalDataSource {

    fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long>

    fun getAllWorkouts(): Observable<ArrayList<WorkoutModel>>

    fun deleteRoutines(workoutId: Long): Completable
}