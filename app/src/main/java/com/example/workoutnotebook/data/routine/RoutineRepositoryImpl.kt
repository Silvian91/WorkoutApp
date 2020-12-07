package com.example.workoutnotebook.data.routine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Single

class RoutineRepositoryImpl(private val routineLocalDataSource: RoutineLocalDataSource) :
    RoutineRepository {

    override fun insertRoutine(routinePairs: List<RoutineModel>): Completable {
        return routineLocalDataSource.insertRoutine(routinePairs)
    }

    override fun getRoutine(workoutId: Long): Single<List<RoutineModel>> {
        return routineLocalDataSource.getRoutines(workoutId)
    }

    override fun getUserRoutine(userId: Long): Single<List<RoutineModel>> {
        return routineLocalDataSource.getUserRoutines(userId)
    }

    override fun deleteRoutine(workoutId: Long): Completable {
        return routineLocalDataSource.deleteRoutines(workoutId)
    }

}