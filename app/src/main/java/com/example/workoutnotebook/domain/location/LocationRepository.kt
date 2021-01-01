package com.example.workoutnotebook.domain.location

import com.example.workoutnotebook.domain.location.model.LocationModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface LocationRepository {

    fun getCurrentLocation(): BehaviorSubject<LocationModel>

}