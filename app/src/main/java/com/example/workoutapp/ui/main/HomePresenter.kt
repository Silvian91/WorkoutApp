package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.Success as QuoteSuccess
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output.Success as WeatherSuccess

class HomePresenter(
    private val compositeDisposable: CompositeDisposable,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getQuoteUseCase: GetQuoteUseCase
) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun start() {
        showWeather()
        showQuote()
    }

    private fun showWeather() {
        getWeatherUseCase.execute(GetWeatherUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is WeatherSuccess -> view.displayWeather(
                        it.weather.name,
                        it.weather.temp.toInt().toString()
                    )
                    else -> view.showNetworkError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun showQuote() {
        getQuoteUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is QuoteSuccess -> view.displayQuote(
                        it.quote.quote,
                        it.quote.author
                    )
                    else -> view.showNetworkError()
                }
            }
            .addTo(compositeDisposable)

    }

    override fun addWorkoutClicked() {
        view.openAddWorkoutActivity()
    }

    override fun showWorkoutClicked() {
        view.openShowWorkoutActivity()
    }


    override fun finish() = compositeDisposable.clear()

}