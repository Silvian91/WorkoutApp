package com.example.workoutapp.domain.weather

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.GetWeatherUseCase.Input
import com.example.workoutapp.domain.weather.GetWeatherUseCase.Output
import com.example.workoutapp.domain.weather.model.WeatherModel

interface GetWeatherUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val locationModel: LocationModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val weather: WeatherModel) : Output()
        object NetworkError : Output()
    }
}