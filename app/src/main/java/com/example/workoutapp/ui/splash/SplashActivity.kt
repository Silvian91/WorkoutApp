package com.example.workoutapp.ui.splash

import android.os.Bundle
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.onboarding.OnboardingActivity
import javax.inject.Inject

class SplashActivity: BaseActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        presenter.setView(this)
        presenter.start()
    }

    override fun openLogin() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun openStart() {
        startActivity(OnboardingActivity.newIntent(this))
        finish()
    }

}