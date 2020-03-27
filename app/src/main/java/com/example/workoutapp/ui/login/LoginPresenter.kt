package com.example.workoutapp.ui.login

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.login.LoginUseCase
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

    override fun start() {
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun onLoginClicked(username: String, password: String) {
        loginUseCase.execute(LoginUseCase.Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LoginUseCase.Output.Success -> view.showMain()
                    is LoginUseCase.Output.ErrorInvalidCredentials -> view.showErrorInvalidCredentials()
                    is LoginUseCase.Output.ErrorUserDoesNotExist -> view.showErrorUserDoesNotExist()
                    else -> view.showUnknownError()
                }
            }
            .addTo(compositeDisposable)
    }

}