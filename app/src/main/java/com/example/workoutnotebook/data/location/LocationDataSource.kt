package com.example.workoutnotebook.data.location

import com.example.workoutnotebook.domain.location.model.LocationModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

interface LocationDataSource {

    fun getCurrentLocation(): BehaviorSubject<LocationModel>

}