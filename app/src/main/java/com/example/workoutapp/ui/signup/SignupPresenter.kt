package com.example.workoutapp.ui.signup

class SignupPresenter : SignupContract.Presenter {

    private lateinit var view: SignupContract.View

    override fun setView(view: SignupContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun registerClicked() {
        view.openRegisterActivity()
    }

    override fun loginClicked() {
        view.openLoginActivity()
    }

    override fun finish() {}

}