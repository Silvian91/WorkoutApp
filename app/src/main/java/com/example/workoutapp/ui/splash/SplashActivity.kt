package com.example.workoutapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.register.RegisterActivity
import javax.inject.Inject

class SplashActivity: AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createSplashComponent().inject(this)

        presenter.setView(this)
        presenter.start()
    }

    override fun openLogin() {
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    override fun openRegister() {
        startActivity(RegisterActivity.newIntent(this))
        finish()
    }

}