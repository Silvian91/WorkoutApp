package com.example.workoutapp.data.user

import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable

interface UserLocalDataSource {

    fun insertUsernameAndPassword(user: ArrayList<UserModel>): Completable

}