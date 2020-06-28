package com.example.workoutapp.ui.splash

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.GetExistingUserUseCase
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Input
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Output.NoUsers as NoExistingUsers
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Output.Success as SuccessExistingUser

class SplashPresenter (
    private val getExistingUserUseCase: GetExistingUserUseCase,
    private val compositeDisposable: CompositeDisposable
): SplashContract.Presenter {

    private lateinit var view: SplashContract.View

    override fun setView(view: SplashContract.View) {
        this.view = view
    }

    override fun start() {
        getExistingUserUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SuccessExistingUser -> view.openLoginActivity()
                    is NoExistingUsers -> view.openRegisterActivity()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun finish() = compositeDisposable.clear()
}