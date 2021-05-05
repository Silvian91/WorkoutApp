package com.rosianu.workoutnotebook.data.session

import com.rosianu.workoutnotebook.domain.session.SessionManager
import io.reactivex.Completable
import io.reactivex.Single

class SessionManagerImpl(
    private val sessionKeyValueDataSource: SessionKeyValueDataSource
) : SessionManager {
    override fun getCurrentUserId(): Single<Long> {
        return sessionKeyValueDataSource.getCurrentUserId()
    }

    override fun setCurrentUserId(id: Long?): Completable {
        return sessionKeyValueDataSource.setCurrentUserId(id)
    }

}