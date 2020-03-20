package com.example.workoutapp.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.register.RegisterActivity
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
        button_register.setOnClickListener { openRegisterActivity() }
        button_login.setOnClickListener { openLoginActivity() }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Workout App"
    }

    private fun openRegisterActivity() {
        startActivity(RegisterActivity.newIntent(this))
    }

    private fun openLoginActivity() {
        startActivity(LoginActivity.newIntent(this))
    }

    override fun onDestroy() {
        button_register.setOnClickListener(null)
        button_login.setOnClickListener(null)

        super.onDestroy()
    }
}