package com.example.workoutnotebook.data.location

import com.example.workoutnotebook.domain.location.LocationRepository
import com.example.workoutnotebook.domain.location.model.LocationModel
import io.reactivex.Completable
import io.reactivex.Single

class LocationRepositoryImpl(private val locationDataSource: LocationDataSource) :
    LocationRepository {

    override fun getCurrentLocation() = locationDataSource.getCurrentLocation()

}