package com.example.workoutnotebook.data.session

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.workoutnotebook.domain.session.UnauthorizedException
import io.reactivex.Completable
import io.reactivex.Single

class SessionKeyValueDataSourceImpl(
    private val sharedPreferences: SharedPreferences
) : SessionKeyValueDataSource {

    override fun getCurrentUserId(): Single<Long> {
        return Single.fromCallable {
            sharedPreferences.contains(CURRENT_USER_ID)
        }
            .map {
                if (it) {
                    sharedPreferences.getLong(CURRENT_USER_ID, 0)
                } else {
                    throw UnauthorizedException()
                }
            }
    }

    @SuppressLint("ApplySharedPref")
    override fun setCurrentUserId(id: Long?): Completable {
        return Completable.fromCallable {
            id?.let {
                sharedPreferences.edit().putLong(CURRENT_USER_ID, it)
                    .commit()
            }
        }
    }

    companion object {
        private const val CURRENT_USER_ID = "CURRENT_USER_ID"
    }
}