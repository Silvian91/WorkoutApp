package com.example.workoutapp.ui.login

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCase.Input
import com.example.workoutapp.domain.login.LoginUseCase.Output.ErrorInvalidCredentials
import com.example.workoutapp.domain.login.LoginUseCase.Output.ErrorUserDoesNotExist
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.error.ErrorType
import com.example.workoutapp.ui.error.ErrorType.Unknown
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.login.LoginUseCase.Output.Success as LoginSuccess

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val showHome = BehaviorSubject.create<Boolean>()
    val showRegister = BehaviorSubject.create<Boolean>()

    fun onLoginClicked(username: String, password: String) {
        loginUseCase.execute(Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LoginSuccess -> showHome.onNext(true)
                    is ErrorInvalidCredentials -> error.onNext(ErrorType.ErrorInvalidCredentials)
                    is ErrorUserDoesNotExist -> error.onNext(ErrorType.ErrorUserNotExist)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onSignUpClicked() = showRegister.onNext(true)

}