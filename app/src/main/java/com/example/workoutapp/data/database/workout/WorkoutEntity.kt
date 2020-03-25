package com.example.workoutapp.data.database.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutapp.domain.workout.model.WorkoutModel

@Entity(tableName = "workout")
data class WorkoutEntity(val title: String) {
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0

    fun toModel(): WorkoutModel {
        return WorkoutModel(
            id,
            title
        )
    }

    companion object {
        fun fromModel(workoutModel: WorkoutModel): WorkoutEntity {
            return WorkoutEntity(
                workoutModel.title
            )
        }
    }


}