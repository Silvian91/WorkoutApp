package com.rosianu.workoutnotebook.data.user

import android.content.Context
import com.rosianu.workoutnotebook.database.WorkoutAppDatabase
import com.rosianu.workoutnotebook.database.user.UserEntity
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class UserLocalDataSourceImpl(val context: Context) : UserLocalDataSource {
    override fun insertUser(user: UserModel): Completable {
        val userDao = WorkoutAppDatabase.getInstance(context).userDao()
        return Single.just(user)
            .map {
                userDao.insertUser(UserEntity.fromModel(it))
            }
            .flatMapCompletable { Completable.complete() }
    }

    override fun getUser(username: String): Maybe<UserModel> {
        return Maybe.fromCallable {
            WorkoutAppDatabase.getInstance(context).userDao().getUser(username)
        }
            .map { it.toModel() }
    }

    override fun getUser(id: Long): Single<UserModel> {
        return Single.fromCallable {
            WorkoutAppDatabase.getInstance(context).userDao().getUser(id)
        }
            .map { it.toModel() }
    }

    override fun getUser(): Maybe<Long> {
        return Maybe.fromCallable {
            WorkoutAppDatabase.getInstance(context).userDao().getUserCount()
        }
            .map { it }
    }

}