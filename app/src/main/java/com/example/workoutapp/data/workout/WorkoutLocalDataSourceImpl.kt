package com.example.workoutapp.data.workout

import android.content.Context
import com.example.workoutapp.data.WorkoutAppDatabase
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class WorkoutLocalDataSourceImpl(val context: Context) : WorkoutLocalDataSource {

    override fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long> {
        return Single.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().insertWorkout(workoutTitleField)
        }
    }

    override fun getAllWorkouts(): Observable<ArrayList<WorkoutModel>> {
        return Observable.fromCallable {
                WorkoutAppDatabase.getInstance(context).workoutDao().getAllWorkouts
            }
            .map {
                val models = ArrayList<WorkoutModel>()
                it.forEach { workout ->
                    models.add(workout.toModel())
                }
                models
            }
    }

    override fun deleteRoutines(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().deleteWorkoutRoutine(workoutId)
        }
    }

}