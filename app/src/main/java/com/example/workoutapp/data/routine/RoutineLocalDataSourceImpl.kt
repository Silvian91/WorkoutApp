package com.example.workoutapp.data.routine

import android.content.Context
import com.example.workoutapp.data.WorkoutAppDatabase
import io.reactivex.Completable
import io.reactivex.Single

class RoutineLocalDataSourceImpl(val context: Context) : RoutineLocalDataSource {

    override fun insertRoutine(routinePairs: List<RoutineEntity>): Completable {
        return Completable.fromCallable {
            val routineDao = WorkoutAppDatabase.getInstance(context).routineDao()
            routinePairs.forEach { routineDao.insertRoutine(it) }
        }
    }

    override fun getRoutines(workoutId: Long): Single<List<RoutineEntity>> {
        return Single.fromCallable {
            WorkoutAppDatabase.getInstance(context).routineDao().getWorkoutRoutines(workoutId)
        }
    }

    override fun deleteRoutines(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).routineDao().deleteWorkoutRoutine(workoutId)
        }
    }

}