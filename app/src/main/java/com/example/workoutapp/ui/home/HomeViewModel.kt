package com.example.workoutapp.ui.home

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType.NetworkError
import com.example.workoutapp.R
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.location.model.LocationModel
import com.example.workoutapp.domain.weather.GetWeatherUseCase
import com.example.workoutapp.domain.weather.model.WeatherModel
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.Success as QuoteSuccess
import com.example.workoutapp.domain.weather.GetWeatherUseCase.Output.Success as WeatherSuccess

class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getQuoteUseCase: GetQuoteUseCase
) : BaseViewModel() {

    private val itemsWrapper = ArrayList<HomeItemWrapper>()
    val data = BehaviorSubject.create<ArrayList<HomeItemWrapper>>()
    val weather = BehaviorSubject.create<WeatherModel>()
    val quote = BehaviorSubject.create<QuoteModel>()
    val showWorkout = BehaviorSubject.create<Boolean>()

    fun showWorkoutsButton() {
        data.onNext(itemsWrapper)
        itemsWrapper.add(
            HomeItemWrapper.Action(
                R.drawable.ic_baseline_fitness_center_24,
                R.string.text_show_workout
            )
        )
    }

    fun fetchWeather(locationModel: LocationModel) {
        getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is WeatherSuccess -> {
                        weather.onNext((it).weather)
                    }
                    else -> error.onNext(NetworkError)
                }
            }
            .addTo(compositeDisposable)
    }


    fun fetchQuote() {
        getQuoteUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is QuoteSuccess -> {
                        quote.onNext((it).quote)
                    }
                    else -> error.onNext(NetworkError)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onShowWorkoutClicked() = showWorkout.onNext(true)

}