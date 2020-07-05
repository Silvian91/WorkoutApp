package com.example.workoutapp.ui.start

class StartPresenter : StartContract.Presenter {

    private lateinit var view: StartContract.View

    override fun setView(view: StartContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun registerClicked() {
        view.openRegister()
    }

    override fun finish() {}

}