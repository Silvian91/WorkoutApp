package com.example.workoutapp.ui.login

class LoginPresenter : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun start() {
    }

    override fun finish() {
    }

}