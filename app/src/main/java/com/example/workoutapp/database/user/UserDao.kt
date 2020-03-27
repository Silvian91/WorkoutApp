package com.example.workoutapp.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUsernameAndPassword(userEntity: UserEntity)


    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    fun getUser(username: String): UserEntity?

}