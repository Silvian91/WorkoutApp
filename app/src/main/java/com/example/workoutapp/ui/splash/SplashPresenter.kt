package com.example.workoutapp.ui.splash

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Input
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBNotEmpty
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output.DBEmpty

class SplashPresenter (
    private val isUserDBEmptyUseCase: IsUserDBEmptyUseCase,
    private val compositeDisposable: CompositeDisposable
): SplashContract.Presenter {

    private lateinit var view: SplashContract.View

    override fun setView(view: SplashContract.View) {
        this.view = view
    }

    override fun start() {
        isUserDBEmptyUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is DBNotEmpty -> view.openLogin()
                    is DBEmpty -> view.openRegister()
                    else -> view.openLogin()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun finish() = compositeDisposable.clear()
}