package com.example.workoutnotebook.domain.session

import io.reactivex.Completable
import io.reactivex.Single

interface SessionManager {

    fun getCurrentUserId(): Single<Long>

    fun setCurrentUserId(id: Long?): Completable

}