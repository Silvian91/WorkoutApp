package com.example.workoutapp.ui.register

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.register.RegisterUseCase
import com.example.workoutapp.domain.register.RegisterUseCase.Input
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class RegisterPresenter(
    private val registerUseCase: RegisterUseCase,
    private val compositeDisposable: CompositeDisposable
) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun finish() = compositeDisposable.clear()

    override fun onContinueClicked(username: String, password: String) {
        registerUseCase.execute(Input(UserModel(username, password)))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is RegisterUseCase.Output.Success -> view.showMain()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

}