package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe

interface UserRepository {

    fun insertUser(user: UserModel): Completable

    fun getUser(username: String): Maybe<UserModel>

}