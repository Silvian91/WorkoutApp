package com.example.workoutnotebook.ui.login

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
import androidx.lifecycle.ViewModelProvider
import com.example.workoutnotebook.R
import com.example.workoutnotebook.R.color.colorPrimary
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.core.ui.BaseActivity
import com.example.core.ui.error.ErrorType
import com.example.core.ui.error.UIError
import com.example.workoutnotebook.ui.main.MainActivity
import com.example.workoutnotebook.ui.register.RegisterActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(LoginViewModel::class.java)

        subscribeToViewState()
        clickLogin()
        setUpSignUpAction()
    }

    private fun subscribeToViewState() {
        viewModel.viewState
            .doOnIoObserveOnMain()
            .subscribeBy { state ->
                openHome(state.home)
                openRegister(state.register)
                showError(state.showError, state.errorType)
            }
            .addTo(compositeDisposable)
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
                viewModel.onSignUpClicked()
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
                viewModel.onLoginClicked(
                    login_username_field.text.toString().toLowerCase(Locale.ENGLISH),
                    login_password_field.text.toString()
                )
            }
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, LoginActivity::class.java)
    }

    private fun showError(showError: Boolean, errorType: UIError.UIErrorFeature?) {
        if (showError) {
            when (errorType) {
                is ErrorType.ErrorInvalidCredentials -> {
                    Snackbar.make(
                        login_layout,
                        getString(R.string.text_error_invalid_credentials),
                        LENGTH_SHORT
                    ).show()
                }
                is ErrorType.Unknown -> {
                    Snackbar.make(
                        login_layout,
                        getString(R.string.text_unknown_error),
                        LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun openHome(showHome: Boolean) {
        if (showHome) {
            startActivity(
                MainActivity.newIntent(this)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    private fun openRegister(showRegister: Boolean) {
        if (showRegister) {
            startActivity(
                RegisterActivity.newIntent(this)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }
}