package com.example.workoutapp.ui.home

import com.example.workoutapp.R
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.error.ErrorType.NetworkError
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.Success as QuoteSuccess
import com.example.workoutapp.domain.openweathermap.GetWeatherUseCase.Output.Success as WeatherSuccess

class HomeViewModel @Inject constructor (
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getQuoteUseCase: GetQuoteUseCase
) : BaseViewModel() {

    private val itemsWrapper = ArrayList<HomeItemWrapper>()
    val weather = BehaviorSubject.create<WeatherModel>()
    val quote = BehaviorSubject.create<QuoteModel>()
    val data = BehaviorSubject.create<ArrayList<HomeItemWrapper>>()
    val showWorkout = BehaviorSubject.create<Boolean>()

    fun showWeatherAndQuote() {
        getWeatherUseCase.execute(GetWeatherUseCase.Input)
            .zipWith(
                getQuoteUseCase.execute(Input)
            )
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it.first) {
                    is WeatherSuccess -> {
                        weather.onNext((it.first as WeatherSuccess).weather)
                    }
                    else -> error.onNext(NetworkError)
                }
                when (it.second) {
                    is QuoteSuccess -> {
                        quote.onNext((it.second as QuoteSuccess).quote)
                        itemsWrapper.add(HomeItemWrapper.Action(
                            R.drawable.ic_baseline_fitness_center_24,
                            R.string.text_show_workout
                        )
                        )
                        data.onNext(itemsWrapper)
                    }
                    else -> error.onNext(NetworkError)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onShowWorkoutClicked() = showWorkout.onNext(true)

}