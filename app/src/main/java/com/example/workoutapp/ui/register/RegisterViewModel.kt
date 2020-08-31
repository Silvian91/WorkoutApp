package com.example.workoutapp.ui.register

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.register.RegisterUseCase
import com.example.workoutapp.domain.register.RegisterUseCase.Input
import com.example.workoutapp.domain.user.model.UserModel
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.error.ErrorType
import com.example.workoutapp.ui.error.ErrorType.ErrorRegistration
import com.example.workoutapp.ui.error.ErrorType.Unknown
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import com.example.workoutapp.domain.login.LoginUseCase.Output.Success as LoginSuccess
import com.example.workoutapp.domain.register.RegisterUseCase.Output.Success as RegistrationSuccess

class RegisterViewModel(
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

    private fun setCurrentUserId(username: String, password: String) {
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