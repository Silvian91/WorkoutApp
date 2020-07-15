package com.example.workoutapp.ui.splash

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Input
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBEmpty
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBNotEmpty
import com.example.workoutapp.ui.common.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    isUserDBEmptyUseCase: IsUserDBEmptyUseCase
) : BaseViewModel() {

    val loginRequest = BehaviorSubject.create<Boolean>()
    val registerRequest = BehaviorSubject.create<Boolean>()

    init {
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

}