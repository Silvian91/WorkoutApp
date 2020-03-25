package com.example.workoutapp.domain.routine

import com.example.workoutapp.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

interface RoutineRepository {

    fun insertRoutine(routinePairs: List<RoutineModel>): Completable

    fun getRoutine(workoutId: Long): Single<List<RoutineModel>>

    fun deleteRoutine(workoutId: Long): Completable
}