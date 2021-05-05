package com.rosianu.workoutnotebook.domain.location

import com.rosianu.workoutnotebook.domain.common.BaseObservableUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase.Input
import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase.Output
import com.rosianu.workoutnotebook.domain.location.model.LocationModel

interface GetLocationUseCase :
    BaseObservableUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val location: LocationModel) : Output()
        object NetworkError : Output()
    }

}