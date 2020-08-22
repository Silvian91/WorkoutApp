package com.example.workoutapp.ui.home

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.Success as QuoteSuccess
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output.Success as WeatherSuccess

class HomePresenter(
    private val compositeDisposable: CompositeDisposable,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getQuoteUseCase: GetQuoteUseCase
) : HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    private val itemsWrapper = ArrayList<HomeItemWrapper>()

    override fun setView(view: HomeContract.View) {
        this.view = view
    }

    override fun start() {
        showWeatherAndQuote()
    }

    private fun showWeatherAndQuote() {
        getWeatherUseCase.execute(GetWeatherUseCase.Input)
            .zipWith(
                getQuoteUseCase.execute(Input)
            )
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it.first) {
                    is WeatherSuccess -> {
                        view.showWeather((it.first as WeatherSuccess).weather)
                    }
                    else -> view.showNetworkError()
                }
                when (it.second) {
                    is QuoteSuccess -> {
                        view.showQoute((it.second as QuoteSuccess).quote)
                        itemsWrapper.add(HomeItemWrapper.Actions)
                        view.showData(itemsWrapper)
                    }
                    else -> view.showNetworkError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onShowWorkoutClicked() {
        view.handleShowWorkoutClick()
    }

    override fun finish() = compositeDisposable.clear()

}