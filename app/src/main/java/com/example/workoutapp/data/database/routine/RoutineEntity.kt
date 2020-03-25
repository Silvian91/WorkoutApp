package com.example.workoutapp.data.database.routine

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.workoutapp.data.database.workout.WorkoutEntity
import com.example.workoutapp.domain.routine.model.RoutineModel

@Entity(
    tableName = "routine",
    foreignKeys = [ForeignKey(
        entity = WorkoutEntity::class,
        parentColumns = ["id"],
        childColumns = ["workoutId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoutineEntity(
    val routineName: String,
    val sets: String,
    val reps: String,
    val weight: String,
    val weightMeasurement: String,
    val rest: String,
    val workoutId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toModel(): RoutineModel {
        return RoutineModel(
            routineName,
            sets,
            reps,
            weight,
            weightMeasurement,
            rest,
            workoutId
        )
    }

    companion object {
        fun fromModel(routineModel: RoutineModel): RoutineEntity {
            return RoutineEntity(
                routineModel.routineName,
                routineModel.sets,
                routineModel.reps,
                routineModel.weight,
                routineModel.weightMeasurement,
                routineModel.rest,
                routineModel.workoutId
            )
        }
    }
}