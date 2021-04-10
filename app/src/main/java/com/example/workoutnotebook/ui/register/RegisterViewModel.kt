package com.example.workoutnotebook.ui.register

import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.login.LoginUseCase
import com.example.workoutnotebook.domain.register.RegisterUseCase
import com.example.workoutnotebook.domain.register.RegisterUseCase.Input
import com.example.workoutnotebook.domain.user.model.UserModel
import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType.ErrorRegistration
import com.example.core.ui.error.ErrorType.Unknown
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutnotebook.domain.login.LoginUseCase.Output.Success as LoginSuccess
import com.example.workoutnotebook.domain.register.RegisterUseCase.Output.Success as RegistrationSuccess

class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val home = BehaviorSubject.create<Boolean>()

    fun onContinueClicked(username: String, password: String, id: Long) {
        registerUseCase.execute(Input(UserModel(username, password, id)))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is RegistrationSuccess -> setCurrentUserId(username, password)
                    else -> error.onNext(ErrorRegistration)
                }
            }
            .addTo(compositeDisposable)
    }

    fun setCurrentUserId(username: String, password: String) {
        loginUseCase.execute(LoginUseCase.Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LoginSuccess -> home.onNext(true)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

}