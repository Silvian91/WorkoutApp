package com.example.workoutapp.ui.register

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.register.RegisterUseCase
import com.example.workoutapp.domain.register.RegisterUseCase.Input
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.login.LoginUseCase.Output.Success as LoginSuccess
import com.example.workoutapp.domain.register.RegisterUseCase.Output.Success as RegistrationSuccess

class RegisterPresenter(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val compositeDisposable: CompositeDisposable
) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun finish() = compositeDisposable.clear()

    override fun onContinueClicked(username: String, password: String, id: Long) {
        registerUseCase.execute(Input(UserModel(username, password, id)))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is RegistrationSuccess -> setCurrentUserId(username, password)
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun setCurrentUserId(username: String, password: String) {
        loginUseCase.execute(LoginUseCase.Input(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LoginSuccess -> view.showHome()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

}