package com.example.workoutapp.data.routine

import android.content.Context
import com.example.workoutapp.data.database.WorkoutAppDatabase
import com.example.workoutapp.data.database.routine.RoutineEntity
import com.example.workoutapp.domain.routine.model.RoutineModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RoutineLocalDataSourceImpl(val context: Context) : RoutineLocalDataSource {

    override fun insertRoutine(routinePairs: List<RoutineModel>): Completable {
        val routineDao = WorkoutAppDatabase.getInstance(context).routineDao()
        return Observable.fromIterable(routinePairs)
            .map {
                routineDao.insertRoutine(RoutineEntity.fromModel(it))
            }
            .toList()
            .flatMapCompletable { Completable.complete() }
    }

    override fun getRoutines(workoutId: Long): Single<ArrayList<RoutineModel>> {
        return Single.fromCallable {
                WorkoutAppDatabase.getInstance(context).routineDao().getWorkoutRoutines(workoutId)
            }
            .map {
                val models = ArrayList<RoutineModel>()
                it.forEach { entity ->
                    models.add(entity.toModel())
                }
                models
            }

    }

    override fun deleteRoutines(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).routineDao().deleteWorkoutRoutine(workoutId)
        }
    }

}