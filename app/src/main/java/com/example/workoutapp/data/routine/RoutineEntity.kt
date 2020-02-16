package com.example.workoutapp.data.routine

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.data.workout.WorkoutEntity

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
    var weightMeasurement: String,
    var rest: String,
    var workoutId: Long
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
            rest
        )
    }
}