package com.example.workoutapp.domain.user.model

data class UserModel(
    val username: String,
    val password: String,
    val id: Long? = null
)