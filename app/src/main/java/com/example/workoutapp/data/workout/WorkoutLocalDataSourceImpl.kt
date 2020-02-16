package com.example.workoutapp.data.workout

import android.content.Context
import com.example.workoutapp.data.WorkoutAppDatabase
import io.reactivex.Completable
import io.reactivex.Single

class WorkoutLocalDataSourceImpl(val context: Context): WorkoutLocalDataSource {

    override fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long> {
        return Single.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().insertWorkout(workoutTitleField)
        }
    }

    override fun getAllWorkouts(): List<WorkoutEntity> {
        return WorkoutAppDatabase.getInstance(context).workoutDao().getAllWorkouts
    }

    override fun deleteRoutines(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().deleteWorkoutRoutine(workoutId)
        }
    }

}