package com.example.workoutapp.data.routine

import com.example.workoutapp.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

interface RoutineRepository {

    fun insertRoutine(routinePairs: List<RoutineModel>): Completable

    fun getRoutine(workoutId: Long): Single<ArrayList<RoutineModel>>

    fun deleteRoutine(workoutId: Long): Completable
}