package com.example.workoutapp.model.routine

import io.reactivex.Completable
import io.reactivex.Single

interface RoutineLocalDataSource {

    fun insertRoutine(routinePairs: List<RoutineEntity>): Completable

    fun getRoutines(workoutId: Long): Single<List<RoutineEntity>>

    fun deleteRoutines(workoutId: Long): Completable

}