package com.example.workoutapp.model.routine

import io.reactivex.Completable
import io.reactivex.Single

interface RoutineRepository {

    fun insertRoutine(routinePairs: List<RoutineEntity>): Completable

    fun getRoutine(workoutId: Long): Single<List<RoutineEntity>>

    fun deleteRoutine(workoutId: Long): Completable
}