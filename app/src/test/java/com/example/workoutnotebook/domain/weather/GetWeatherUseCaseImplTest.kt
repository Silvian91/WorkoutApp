package com.example.workoutnotebook.domain.weather

import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.model.WeatherModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetWeatherUseCaseImplTest {

    private val repository: WeatherRepository = mockk()
    private lateinit var useCase: GetWeatherUseCase
    private val model = WeatherModel(22.22, "Berlin")
    private val locationModel = LocationModel(11.22, 22.11)

    @BeforeEach
    fun setUp() {
        useCase = GetWeatherUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getCurrentWeather(locationModel) } returns Single.just(model)

        useCase.execute(GetWeatherUseCase.Input(locationModel)).test()
            .assertValue(GetWeatherUseCase.Output.Success(model))
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getCurrentWeather(locationModel) } returns Single.error(RuntimeException())

        useCase.execute(GetWeatherUseCase.Input(locationModel)).test()
            .assertValue(GetWeatherUseCase.Output.NetworkError)
    }

}