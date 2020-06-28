package com.example.workoutapp.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_unknown_error
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_register.*
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

    override fun openLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun openRegisterActivity() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun showError() = Snackbar.make(
        window.decorView.rootView,
        text_unknown_error,
        LENGTH_SHORT
    ).show()
}