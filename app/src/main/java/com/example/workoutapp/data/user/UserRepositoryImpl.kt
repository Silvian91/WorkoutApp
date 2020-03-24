package com.example.workoutapp.data.user

import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable

class UserRepositoryImpl(private val userLocalDataSource: UserLocalDataSource): UserRepository {

    override fun insertUsernameAndPassword(user: ArrayList<UserModel>): Completable {
        return userLocalDataSource.insertUsernameAndPassword(user)
    }

}