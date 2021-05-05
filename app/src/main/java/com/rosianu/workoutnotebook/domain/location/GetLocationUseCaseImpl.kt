package com.rosianu.workoutnotebook.domain.location

import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase.Output
import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase.Output.NetworkError
import io.reactivex.Observable

class GetLocationUseCaseImpl(
    private val locationRepository: LocationRepository
) : GetLocationUseCase {

    override fun execute(input: GetLocationUseCase.Input): Observable<Output> {
        return locationRepository.getCurrentLocation()
            .map { Output.Success(it) as Output }
            .onErrorReturn { NetworkError }
    }

}