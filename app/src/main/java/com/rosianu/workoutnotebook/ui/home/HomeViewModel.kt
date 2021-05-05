package com.rosianu.workoutnotebook.ui.home

import com.rosianu.core.ui.BaseViewModel
import com.rosianu.core.ui.error.ErrorType.NetworkError
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Input
import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.rosianu.workoutnotebook.domain.location.GetLocationUseCase
import com.rosianu.workoutnotebook.domain.location.model.LocationModel
import com.rosianu.workoutnotebook.domain.weather.GetWeatherUseCase
import com.rosianu.workoutnotebook.domain.weather.model.WeatherModel
import com.rosianu.workoutnotebook.ui.home.adapter.HomeItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output.Success as QuoteSuccess

class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getQuoteUseCase: GetQuoteUseCase,
    private val getLocationUseCase: GetLocationUseCase,
) : BaseViewModel() {

    private val itemsWrapper = ArrayList<HomeItemWrapper>()
    val data = BehaviorSubject.create<ArrayList<HomeItemWrapper>>()
    val weather = BehaviorSubject.create<WeatherModel>()
    val quote = BehaviorSubject.create<QuoteModel>()
    val showWorkout = BehaviorSubject.create<Boolean>()
    val location = BehaviorSubject.create<LocationModel>()

    fun showWorkoutsButton() {
        data.onNext(itemsWrapper)
        itemsWrapper.add(
            HomeItemWrapper.Action(
                R.drawable.ic_baseline_fitness_center_24,
                R.string.text_show_workout
            )
        )
    }

    fun fetchCurrentLocation() {
        getLocationUseCase.execute(GetLocationUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetLocationUseCase.Output.Success -> {
                        location.onNext((it).location)
                    }
                    else -> error.onNext(NetworkError)
                }
            }
            .addTo(compositeDisposable)
    }

//    fun fetchWeather(locationModel: LocationModel) {
//        getWeatherUseCase.execute(GetWeatherUseCase.Input(locationModel))
//            .doOnIoObserveOnMain()
//            .subscribeBy {
//                when (it) {
//                    is WeatherSuccess -> {
//                        weather.onNext((it).weather)
//                    }
//                    else -> error.onNext(NetworkError)
//                }
//            }
//            .addTo(compositeDisposable)
//    }

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