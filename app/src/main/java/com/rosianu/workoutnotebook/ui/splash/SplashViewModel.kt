package com.rosianu.workoutnotebook.ui.splash

import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Input
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output.DBEmpty
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output.DBNotEmpty
import com.rosianu.core.ui.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    isUserDBEmptyUseCase: IsUserDBEmptyUseCase
) : BaseViewModel() {

    val loginRequest = BehaviorSubject.create<Boolean>()
    val onboardingRequest = BehaviorSubject.create<Boolean>()

    init {
        isUserDBEmptyUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is DBNotEmpty -> loginRequest.onNext(true)
                    is DBEmpty -> onboardingRequest.onNext(true)
                    else -> loginRequest.onNext(true)
                }
            }
            .addTo(compositeDisposable)
    }

}