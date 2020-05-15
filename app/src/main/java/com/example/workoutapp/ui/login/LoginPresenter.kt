package com.example.workoutapp.ui.login

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCase.Input
import com.example.workoutapp.domain.login.LoginUseCase.Output.*
import com.example.workoutapp.ui.login.LoginContract.ErrorType.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class LoginPresenter(
    private val loginUseCase: LoginUseCase,
    private val compositeDisposable: CompositeDisposable
) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun finish() = compositeDisposable.clear()

    override fun onLoginClicked(username: String, password: String) {
        loginUseCase.execute(Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is Success -> view.showHome()
                    is ErrorInvalidCredentials -> view.showError(
                        INVALID_CREDENTIALS
                    )
                    is ErrorUserDoesNotExist -> view.showError(
                        USER_DOES_NOT_EXIST
                    )
                    else -> view.showError(UNKNOWN)
                }
            }
            .addTo(compositeDisposable)
    }

}