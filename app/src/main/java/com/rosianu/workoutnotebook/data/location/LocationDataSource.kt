package com.rosianu.workoutnotebook.data.location

import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import io.reactivex.subjects.BehaviorSubject

interface LocationDataSource {

    fun getCurrentLocation(): BehaviorSubject<LocationModel>

}