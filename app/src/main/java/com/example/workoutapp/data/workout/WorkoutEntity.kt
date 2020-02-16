package com.example.workoutapp.data.workout

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutapp.domain.workout.model.WorkoutModel

@Entity(tableName = "workout")
data class WorkoutEntity(val title: String ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toModel(): WorkoutModel {
        return WorkoutModel(
            title
        )
    }
}