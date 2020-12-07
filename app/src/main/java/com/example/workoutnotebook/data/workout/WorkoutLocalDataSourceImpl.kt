package com.example.workoutnotebook.data.workout

import android.content.Context
import com.example.workoutnotebook.database.WorkoutAppDatabase
import com.example.workoutnotebook.database.workout.WorkoutEntity
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

class WorkoutLocalDataSourceImpl(val context: Context) : WorkoutLocalDataSource {

    override fun insertWorkout(workoutModel: WorkoutModel): Single<Long> {
        return Single.just(workoutModel)
            .map { WorkoutEntity.fromModel(it) }
            .map { WorkoutAppDatabase.getInstance(context).workoutDao().insertWorkout(it) }
    }

    override fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>> {
        return Single.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().getAllWorkouts(userId)
        }
            .map {
                val models = ArrayList<WorkoutModel>()
                it.forEach { workout ->
                    models.add(workout.toModel())
                }
                models
            }
    }

    override fun deleteWorkout(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().deleteWorkoutFromRoutine(workoutId)
        }
    }

    override fun softDeleteWorkout(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().softDeleteWorkout(workoutId)
        }
    }

    override fun undoSoftDeleteWorkout(workoutId: Long): Completable {
        return Completable.fromCallable {
            WorkoutAppDatabase.getInstance(context).workoutDao().undoSoftDeleteWorkout(workoutId)
        }
    }

}