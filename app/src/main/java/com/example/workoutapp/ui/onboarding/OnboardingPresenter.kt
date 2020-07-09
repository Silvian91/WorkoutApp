package com.example.workoutapp.ui.onboarding

class OnboardingPresenter : OnboardingContract.Presenter {

    private lateinit var view: OnboardingContract.View

    override fun setView(view: OnboardingContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun registerClicked() {
        view.openRegister()
    }

    override fun finish() {}

}