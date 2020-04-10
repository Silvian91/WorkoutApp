package com.example.workoutapp.data.user

import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe

class UserRepositoryImpl(private val userLocalDataSource: UserLocalDataSource) : UserRepository {

    override fun insertUser(user: UserModel): Completable {
        return userLocalDataSource.insertUser(user)
    }

    override fun getUser(username: String): Maybe<UserModel> {
        return userLocalDataSource.getUser(username)
    }

}