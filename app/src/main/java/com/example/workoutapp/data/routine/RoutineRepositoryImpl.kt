package com.example.workoutapp.data.routine

import io.reactivex.Completable
import io.reactivex.Single

class RoutineRepositoryImpl(private val routineLocalDataSource: RoutineLocalDataSource) : RoutineRepository {

    override fun insertRoutine(routinePairs: List<RoutineEntity>): Completable {
        return routineLocalDataSource.insertRoutine(routinePairs)
    }

    override fun getRoutine(workoutId: Long): Single<List<RoutineEntity>> {
        return routineLocalDataSource.getRoutines(workoutId)
    }

    override fun deleteRoutine(workoutId: Long): Completable {
        return routineLocalDataSource.deleteRoutines(workoutId)
    }

}