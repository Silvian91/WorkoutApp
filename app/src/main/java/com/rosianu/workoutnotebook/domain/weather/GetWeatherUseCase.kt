package com.rosianu.workoutnotebook.domain.weather

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Input
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Output
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel

interface GetWeatherUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val locationModel: LocationModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val weather: WeatherModel) : Output()
        object NetworkError : Output()
    }
}