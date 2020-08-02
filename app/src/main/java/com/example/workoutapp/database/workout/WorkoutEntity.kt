package com.example.workoutapp.database.workout

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.workoutapp.database.user.UserEntity
import com.example.workoutapp.domain.workout.model.WorkoutModel

@Entity(
    tableName = "workout",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WorkoutEntity(val title: String, val userId: Long, val isDeleted : Long = 0) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toModel() = WorkoutModel(id, title, userId)

    companion object {
        fun fromModel(workoutModel: WorkoutModel) =
            WorkoutEntity(workoutModel.title, workoutModel.userId)
    }

}