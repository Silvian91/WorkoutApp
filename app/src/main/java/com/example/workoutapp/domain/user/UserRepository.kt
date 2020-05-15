package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository {

    fun insertUser(user: UserModel): Completable

    fun getUser(username: String): Maybe<UserModel>

    fun getUser(id: Long): Single<UserModel>

}