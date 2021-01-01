package com.example.workoutnotebook.database.routine

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.workoutnotebook.database.workout.WorkoutEntity
import com.example.workoutnotebook.domain.routine.model.RoutineModel

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
    var routineName: String,
    var sets: String,
    var reps: String,
    var weight: String,
    var rest: String,
    var workoutId: Long,
    var userId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toModel(): RoutineModel {
        return RoutineModel(
            routineName,
            sets,
            reps,
            weight,
            rest,
            workoutId,
            userId
        )
    }

    companion object {
        fun fromModel(routineModel: RoutineModel): RoutineEntity {
            return RoutineEntity(
                routineModel.routineName,
                routineModel.sets,
                routineModel.reps,
                routineModel.weight,
                routineModel.rest,
                routineModel.workoutId,
                routineModel.userId
            )
        }
    }
}