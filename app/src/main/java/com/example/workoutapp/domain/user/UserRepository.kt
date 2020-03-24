package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable

interface UserRepository {

    fun insertUsernameAndPassword(user: ArrayList<UserModel>): Completable

}