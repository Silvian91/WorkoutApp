package com.example.workoutnotebook.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.workoutnotebook.domain.user.model.UserModel

@Entity(tableName = "users")
data class UserEntity(val username: String, val password: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun toModel(): UserModel {
        return UserModel(
            username,
            password,
            id
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