package com.example.workoutnotebook.domain.weather

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.GetWeatherUseCase.Input
import com.example.workoutnotebook.domain.weather.GetWeatherUseCase.Output
import com.example.workoutnotebook.domain.weather.model.WeatherModel

interface GetWeatherUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val locationModel: LocationModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val weather: WeatherModel) : Output()
        object NetworkError : Output()
    }
}