package com.example.workoutapp.ui.login

import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCase.Output.*
import com.example.workoutapp.ui.login.LoginContract.ErrorType.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { it ->
                when (it) {
                    is Success -> view.showMain()
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