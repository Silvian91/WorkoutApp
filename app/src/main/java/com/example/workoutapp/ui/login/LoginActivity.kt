package com.example.workoutapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginContract.ErrorType
import com.example.workoutapp.ui.login.LoginContract.ErrorType.*
import com.example.workoutapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createLoginComponent().inject(this)
        setContentView(R.layout.activity_login)

        presenter.setView(this)
        button_confirm_login
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                presenter.onLoginClicked(
                    login_username_field.text.toString().toLowerCase(Locale.ENGLISH),
                    login_password_field.text.toString()
                )
            }
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, LoginActivity::class.java)
    }

    override fun showHome() {
        startActivity(
            MainActivity.newIntent(this)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showError(errorType: ErrorType) {
        when (errorType) {
            INVALID_CREDENTIALS -> Snackbar.make(
                login_layout,
                INVALID_CREDENTIALS.error,
                LENGTH_SHORT
            ).show()
            USER_DOES_NOT_EXIST -> Snackbar.make(
                login_layout,
                USER_DOES_NOT_EXIST.error,
                LENGTH_SHORT
            ).show()
            else -> {
                Snackbar.make(login_layout, UNKNOWN.error, LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }
}