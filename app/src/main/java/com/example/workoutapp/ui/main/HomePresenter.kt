package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import com.example.workoutapp.domain.openweathermap.OpenWeatherMapRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

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
        showOpenWeatherAPI()
        showInspirationalQuote()
    }

    private fun showOpenWeatherAPI() {
        getWeatherUseCase.execute(GetWeatherUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetWeatherUseCase.Output.Success -> view.displayCurrentWeather(it.weather)
                    else -> view.showNetworkError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun showInspirationalQuote() {
        getQuoteUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetQuoteUseCase.Output.Success -> view.displayQuote(it.quote)
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