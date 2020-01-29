package com.example.workoutapp.model.workout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")
data class WorkoutEntity(val title: String ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}