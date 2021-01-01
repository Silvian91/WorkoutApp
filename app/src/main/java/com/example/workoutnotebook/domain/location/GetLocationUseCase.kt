package com.example.workoutnotebook.domain.location

import com.example.workoutnotebook.domain.common.BaseObservableUseCase
import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.location.GetLocationUseCase.Input
import com.example.workoutnotebook.domain.location.GetLocationUseCase.Output
import com.example.workoutnotebook.domain.location.model.LocationModel

interface GetLocationUseCase :
    BaseObservableUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val location: LocationModel) : Output()
        object NetworkError : Output()
    }

}