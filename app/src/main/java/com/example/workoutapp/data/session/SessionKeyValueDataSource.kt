package com.example.workoutapp.data.session

import io.reactivex.Completable
import io.reactivex.Single

interface SessionKeyValueDataSource {

    fun getCurrentUserId(): Single<Long>

    fun setCurrentUserId(id: Long): Completable

}