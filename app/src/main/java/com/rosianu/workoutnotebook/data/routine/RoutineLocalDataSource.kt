package com.rosianu.workoutnotebook.data.routine

import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

interface RoutineLocalDataSource {

    fun insertRoutine(routinePairs: List<RoutineModel>): Completable

    fun getRoutines(workoutId: Long): Single<List<RoutineModel>>

    fun getUserRoutines(userId: Long): Single<List<RoutineModel>>

    fun deleteRoutines(workoutId: Long): Completable

}