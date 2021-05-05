package com.rosianu.workoutnotebook.ui.home

import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase
import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase
import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewModelTest : BaseTest() {
    private lateinit var viewModel: HomeViewModel
    private val getWeatherUseCase: GetWeatherUseCase = mockk()
    private val getQuoteUseCase: GetQuoteUseCase = mockk()
    private val getLocationUseCase: GetLocationUseCase = mockk()
    private val locationModel = LocationModel(22.22, 33.11)
    private val weatherModel = WeatherModel(22.5, "Berlin")
    private val quoteModel = QuoteModel("quote", "author")

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = HomeViewModel(
                getWeatherUseCase,
                getQuoteUseCase,
                getLocationUseCase
        )
    }

    @Test
    fun `get current location returns expected output on success`(){
        every { getLocationUseCase.execute(GetLocationUseCase.Input) } returns
                Observable.just(GetLocationUseCase.Output.Success(locationModel))

        viewModel.fetchCurrentLocation()

        getLocationUseCase.execute(GetLocationUseCase.Input).test()
                .assertValue(GetLocationUseCase.Output.Success(locationModel))
    }

    @Test
    fun `get current location returns expected output network error`(){
        every { getLocationUseCase.execute(GetLocationUseCase.Input) } returns
                Observable.just(GetLocationUseCase.Output.NetworkError)

        viewModel.fetchCurrentLocation()

        getLocationUseCase.execute(GetLocationUseCase.Input).test()
                .assertValue(GetLocationUseCase.Output.NetworkError)
    }

    @Test
    fun `get weather returns expected output on success`(){
        every { getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel)) } returns
                Single.just(GetWeatherUseCase.Output.Success(weatherModel))

        viewModel.fetchWeather(locationModel)

        getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel)).test()
                .assertValue(GetWeatherUseCase.Output.Success(weatherModel))
    }

    @Test
    fun `get weather returns expected output network error`(){
        every { getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel)) } returns
                Single.just(GetWeatherUseCase.Output.NetworkError)

        viewModel.fetchWeather(locationModel)

        getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel)).test()
                .assertValue(GetWeatherUseCase.Output.NetworkError)
    }

    @Test
    fun `get quote returns expected output on success`(){
        every { getQuoteUseCase.execute(GetQuoteUseCase.Input) } returns
                Single.just(GetQuoteUseCase.Output.Success(quoteModel))

        viewModel.fetchQuote()

        getQuoteUseCase.execute(GetQuoteUseCase.Input).test()
                .assertValue(GetQuoteUseCase.Output.Success(quoteModel))
    }

    @Test
    fun `get quote returns expected output network error`(){
        every { getQuoteUseCase.execute(GetQuoteUseCase.Input) } returns
                Single.just(GetQuoteUseCase.Output.NetworkError)

        viewModel.fetchQuote()

        getQuoteUseCase.execute(GetQuoteUseCase.Input).test()
                .assertValue(GetQuoteUseCase.Output.NetworkError)
    }

}