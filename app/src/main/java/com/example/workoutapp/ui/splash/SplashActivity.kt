package com.example.workoutapp.ui.splash

import android.os.Bundle
import com.example.workoutapp.ui.common.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.onboarding.OnboardingActivity
import javax.inject.Inject
import com.example.workoutapp.ui.register.RegisterActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SplashActivity: BaseActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        subscribeToViewModel()
    }

    private fun openLogin() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    private fun openRegister() {
        startActivity(RegisterActivity.newIntent(this))
        finish()
    }

    private fun subscribeToViewModel() {
        viewModel.loginRequest
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribeBy {
                openLogin()
            }
            .addTo(compositeDisposable)

        viewModel.registerRequest
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribeBy {
                openRegister()
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}