package com.example.workoutnotebook.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUser(username: String): UserEntity

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    fun getUser(id: Long): UserEntity

    @Query("SELECT COUNT(*) from users")
    fun getUserCount(): Long

}