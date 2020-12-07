package com.example.workoutnotebook.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.ui.login.LoginActivity
import com.example.workoutnotebook.ui.onboarding.OnboardingActivity
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory.get()).get(SplashViewModel::class.java)
        subscribeToViewModel()
    }

    private fun openLogin() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    private fun openOnboarding() {
        startActivity(OnboardingActivity.newIntent(this))
        finish()
    }

    private fun subscribeToViewModel() {
        viewModel.loginRequest
            .doOnIoObserveOnMain()
            .subscribeBy {
                openLogin()
            }
            .addTo(compositeDisposable)

        viewModel.onboardingRequest
            .doOnIoObserveOnMain()
            .subscribeBy {
                openOnboarding()
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}