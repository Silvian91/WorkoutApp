package com.rosianu.workoutnotebook.domain.weather

import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Input
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Output
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Output.NetworkError
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase.Output.Success
import io.reactivex.Single

class GetWeatherUseCaseImpl(
    private val weatherRepository: WeatherRepository
) : GetWeatherUseCase {
    override fun execute(input: Input): Single<Output> {
        return weatherRepository.getCurrentWeather(input.locationModel)
            .map { Success(it) as Output }
            .onErrorReturn { NetworkError }
    }

}