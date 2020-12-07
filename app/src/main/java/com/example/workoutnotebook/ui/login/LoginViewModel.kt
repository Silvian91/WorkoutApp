package com.example.workoutnotebook.ui.login

import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.login.LoginUseCase
import com.example.workoutnotebook.domain.login.LoginUseCase.Input
import com.example.workoutnotebook.domain.login.LoginUseCase.Output.ErrorInvalidCredentials
import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.core.ui.error.ErrorType.Unknown
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutnotebook.domain.login.LoginUseCase.Output.Success as LoginSuccess

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private var currentViewState = LoginViewState(
        home = false,
        register = false,
        showError = false
    )

    val viewState = BehaviorSubject.createDefault(currentViewState)

    fun onLoginClicked(username: String, password: String) {
        loginUseCase.execute(Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LoginSuccess -> {
                        currentViewState = currentViewState.copy(
                            home = true,
                            register = false,
                            showError = false
                        )
                    }
                    is ErrorInvalidCredentials -> {

                        currentViewState = currentViewState.copy(
                            home = false,
                            showError = true,
                            errorType = ErrorType.ErrorInvalidCredentials)
                    }
                    else -> {
                        currentViewState = currentViewState.copy(
                            home = false,
                            showError = true,
                            errorType = Unknown)
                    }
                }
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    fun onSignUpClicked() {
        currentViewState = currentViewState.copy(register = true)
        viewState.onNext(currentViewState)
    }

}