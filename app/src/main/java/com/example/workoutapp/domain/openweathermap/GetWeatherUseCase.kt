package com.example.workoutapp.domain.openweathermap

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Input
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output
import com.example.workoutapp.domain.openweathermap.model.WeatherModel

interface GetWeatherUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val weather: WeatherModel) : Output()
        object NetworkError : Output()
    }
}