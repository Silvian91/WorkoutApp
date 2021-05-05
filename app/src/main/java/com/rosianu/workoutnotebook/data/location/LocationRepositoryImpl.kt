package com.rosianu.workoutnotebook.data.location

import com.rosianu.workoutnotebook.domain.location.LocationRepository

class LocationRepositoryImpl(private val locationDataSource: LocationDataSource) :
    LocationRepository {

    override fun getCurrentLocation() = locationDataSource.getCurrentLocation()

}