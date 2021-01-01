package com.example.workoutnotebook.domain.location

import com.example.workoutnotebook.domain.location.GetLocationUseCase.Output
import com.example.workoutnotebook.domain.location.GetLocationUseCase.Output.NetworkError
import io.reactivex.Observable
import io.reactivex.Single

class GetLocationUseCaseImpl(
    private val locationRepository: LocationRepository
) : GetLocationUseCase {

    override fun execute(input: GetLocationUseCase.Input): Observable<Output> {
        return locationRepository.getCurrentLocation()
            .map { Output.Success(it) as Output }
            .onErrorReturn { NetworkError }
    }

}