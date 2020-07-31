package com.example.workoutapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.example.workoutapp.R
import com.example.workoutapp.R.color.colorPrimary
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.login.LoginContract.ErrorType
import com.example.workoutapp.ui.login.LoginContract.ErrorType.INVALID_CREDENTIALS
import com.example.workoutapp.ui.login.LoginContract.ErrorType.USER_DOES_NOT_EXIST
import com.example.workoutapp.ui.main.MainActivity
import com.example.workoutapp.ui.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        presenter.setView(this)

        clickLogin()
        setUpSignUpAction()
    }

    private fun setUpSignUpAction() {
        val signUpString = getString(R.string.text_login_no_account_yet)
        val spannableString = SpannableString(signUpString)

        val setColor = ForegroundColorSpan(resources.getColor(colorPrimary, null))

        val clickAction = object : ClickableSpan() {
            override fun updateDrawState(drawState: TextPaint) {
                drawState.isUnderlineText = false
            }

            override fun onClick(widget: View) {
                presenter.onSignUpClicked()
            }
        }

        spannableString.setSpan(clickAction, 23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(setColor, 23, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        text_sign_up.text = spannableString
        text_sign_up.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun clickLogin() {
        button_confirm_login
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
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
                getString(R.string.text_error_invalid_credentials),
                LENGTH_SHORT
            ).show()
            USER_DOES_NOT_EXIST -> Snackbar.make(
                login_layout,
                getString(R.string.text_error_invalid_credentials),
                LENGTH_SHORT
            ).show()
            else -> {
                Snackbar.make(login_layout, getString(R.string.text_unknown_error), LENGTH_SHORT).show()
            }
        }
    }

    override fun openRegisterActivity() {
        startActivity(
            RegisterActivity.newIntent(this)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }
}