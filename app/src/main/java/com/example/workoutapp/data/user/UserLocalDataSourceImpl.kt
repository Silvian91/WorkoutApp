package com.example.workoutapp.data.user

import android.content.Context
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable

class UserLocalDataSourceImpl(context: Context): UserLocalDataSource {
    override fun insertUsernameAndPassword(user: ArrayList<UserModel>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}