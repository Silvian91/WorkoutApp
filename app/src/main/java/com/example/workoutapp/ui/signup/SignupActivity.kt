package com.example.workoutapp.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.register.RegisterActivity
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

class SignupActivity : AppCompatActivity(), SignupContract.View {

    @Inject
    lateinit var presenter: SignupContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createSignupComponent().inject(this)
        setContentView(R.layout.activity_signup)

        setToolbar()
        presenter.setView(this)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        button_register
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe { presenter.registerClicked() }
        button_login
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe { presenter.loginClicked() }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Workout App"
    }

    override fun openRegisterActivity() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun openLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, SignupActivity::class.java)
    }

}