package com.example.workoutapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createLoginComponent().inject(this)
        setContentView(R.layout.activity_login)

        presenter.setView(this)
        button_confirm_login.setOnClickListener {
            presenter.onLoginClicked(
                login_username_field.text.toString(),
                login_password_field.text.toString()
            )
        }
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, LoginActivity::class.java)
    }

    override fun showMain() {
        Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorInvalidCredentials() {
        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorUserDoesNotExist() {
        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
    }

    override fun showUnknownError() {
        Toast.makeText(this, "Unknown", Toast.LENGTH_SHORT).show()
    }
}