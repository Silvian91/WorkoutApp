package com.example.workoutapp.data.user

import android.content.Context
import com.example.workoutapp.database.WorkoutAppDatabase
import com.example.workoutapp.database.user.UserEntity
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

class UserLocalDataSourceImpl(val context: Context) : UserLocalDataSource {
    override fun insertUsernameAndPassword(user: ArrayList<UserModel>): Completable {
        val userDao = WorkoutAppDatabase.getInstance(context).userDao()
        return Observable.fromIterable(user)
            .map {
                userDao.insertUsernameAndPassword(UserEntity.fromModel(it))
            }
            .toList()
            .flatMapCompletable { Completable.complete() }
    }

    override fun getUser(username: String): Maybe<UserModel> {
        return Maybe.just(
            WorkoutAppDatabase.getInstance(context).userDao().getUser(username)
        ).map { it.toModel() }
    }

}