package com.example.workoutapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.register.RegisterActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

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

    private fun openRegister() {
        startActivity(RegisterActivity.newIntent(this))
        finish()
    }

    private fun subscribeToViewModel() {
        viewModel.loginRequest
            .doOnIoObserveOnMain()
            .subscribeBy {
                openLogin()
            }
            .addTo(compositeDisposable)

        viewModel.registerRequest
            .doOnIoObserveOnMain()
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