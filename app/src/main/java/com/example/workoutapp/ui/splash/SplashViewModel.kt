package com.example.workoutapp.ui.splash

import androidx.lifecycle.ViewModel
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Input
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBNotEmpty
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBEmpty
import io.reactivex.subjects.BehaviorSubject

class SplashViewModel (
    private val isUserDBEmptyUseCase: IsUserDBEmptyUseCase,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {

    val loginRequest = BehaviorSubject.create<Boolean>()
    val registerRequest = BehaviorSubject.create<Boolean>()

    fun start() {
        isUserDBEmptyUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is DBNotEmpty -> loginRequest.onNext(true)
                    is DBEmpty -> registerRequest.onNext(true)
                    else -> loginRequest.onNext(true)
                }
            }
            .addTo(compositeDisposable)
    }

    fun finish() = compositeDisposable.clear()
}