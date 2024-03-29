package com.rosianu.workoutnotebook.data.user

import com.rosianu.workoutnotebook.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserLocalDataSource {

    fun insertUser(user: UserModel): Completable

    fun getUser(username: String): Maybe<UserModel>

    fun getUser(id: Long): Single<UserModel>

    fun getUser(): Maybe<Long>

}