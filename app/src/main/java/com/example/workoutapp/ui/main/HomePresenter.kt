package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.InspirationalQuoteRepository
import com.example.workoutapp.domain.openweathermap.OpenWeatherMapRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class HomePresenter(
    private val compositeDisposable: CompositeDisposable,
    private val openWeatherMapRepository: OpenWeatherMapRepository,
    private val inspirationalQuoteRepository: InspirationalQuoteRepository
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
        openWeatherMapRepository.getCurrentWeather()
            .doOnIoObserveOnMain()
            .subscribe { weather -> view.displayCurrentWeather(weather) }
            .addTo(compositeDisposable)
    }

    private fun showInspirationalQuote() {
        inspirationalQuoteRepository.getRandomQuote()
            .doOnIoObserveOnMain()
            .subscribe { quote -> view.displayQuote(quote) }
            .addTo(compositeDisposable)
    }

    override fun finish() = compositeDisposable.clear()

}