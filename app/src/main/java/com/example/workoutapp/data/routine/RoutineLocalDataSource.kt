package com.example.workoutapp.data.routine

import com.example.workoutapp.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

interface RoutineLocalDataSource {

    fun insertRoutine(routinePairs: List<RoutineModel>): Completable

    fun getRoutines(workoutId: Long): Single<ArrayList<RoutineModel>>

    fun deleteRoutines(workoutId: Long): Completable

}