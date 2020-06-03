package com.example.workoutapp.domain.openweathermap

import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Input
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output.NetworkError
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output.Success
import io.reactivex.Single

class GetWeatherUseCaseImpl(
    private val openWeatherMapRepository: OpenWeatherMapRepository
) : GetWeatherUseCase {
    override fun execute(input: Input): Single<Output> {
        return openWeatherMapRepository.getCurrentWeather()
            .map { Success(it) as Output}
            .onErrorReturn { NetworkError }
    }

}