package com.example.workoutapp.data.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutapp.domain.user.model.UserModel

@Entity(tableName = "user")
data class UserEntity(val username: String, val password: String) {
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0

    fun toModel(): UserModel {
        return UserModel(
            username,
            password
        )
    }

    companion object {
        fun fromModel(userModel: UserModel): UserEntity {
            return UserEntity(
                userModel.username,
                userModel.password
            )
        }
    }

}