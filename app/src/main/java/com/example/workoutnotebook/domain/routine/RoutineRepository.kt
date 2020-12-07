package com.example.workoutnotebook.domain.routine

import com.example.workoutnotebook.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

interface RoutineRepository {

    fun insertRoutine(routinePairs: List<RoutineModel>): Completable

    fun getRoutine(workoutId: Long): Single<List<RoutineModel>>

    fun getUserRoutine(userId: Long): Single<List<RoutineModel>>

    fun deleteRoutine(workoutId: Long): Completable
}