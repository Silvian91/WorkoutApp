package com.example.workoutapp.data.user

import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class UserRepositoryImpl(private val userLocalDataSource: UserLocalDataSource) : UserRepository {

    override fun insertUser(user: UserModel): Completable {
        return userLocalDataSource.insertUser(user)
    }

    override fun getUserCount(username: String): Maybe<UserModel> {
        return userLocalDataSource.getUser(username)
    }

    override fun getUserCount(id: Long): Single<UserModel> {
        return userLocalDataSource.getUser(id)
    }

    override fun getUserCount(): Maybe<Long> {
        return userLocalDataSource.getUser()
    }

}