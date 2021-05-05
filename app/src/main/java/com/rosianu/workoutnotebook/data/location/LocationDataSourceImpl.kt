package com.rosianu.workoutnotebook.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import io.reactivex.subjects.BehaviorSubject

class LocationDataSourceImpl(
    private val context: Context
) : LocationDataSource {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private val currentLocation = BehaviorSubject.create<LocationModel>()


    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): BehaviorSubject<LocationModel> {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                requestLocationData()
            } else {
                currentLocation.onNext(
                    LocationModel(
                        location.longitude,
                        location.latitude
                    )
                )
            }
        }
        return currentLocation
    }


    @SuppressLint("MissingPermission")
    private fun requestLocationData() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

}