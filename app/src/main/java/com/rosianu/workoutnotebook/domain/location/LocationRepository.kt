package com.rosianu.workoutnotebook.domain.location

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import io.reactivex.subjects.BehaviorSubject

interface LocationRepository {

    fun getCurrentLocation(): BehaviorSubject<LocationModel>

}