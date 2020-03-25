package com.example.workoutapp.ui.login

import com.example.workoutapp.domain.user.UserRepository
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(
    private val userRepository: UserRepository,
    private val compositeDisposable: CompositeDisposable
) : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun start() {
    }

    override fun finish() {
    }

}