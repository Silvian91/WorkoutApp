package com.example.workoutapp.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_error_registration_failed
import com.example.workoutapp.R.string.text_unknown_error
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.error.ErrorType
import com.example.workoutapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : BaseActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(RegisterViewModel::class.java)

        setOnClickListenerEvent()

        register_username_field.addTextChangedListener(setTextWatcher)
        register_password_field.addTextChangedListener(setTextWatcher)

        homeResponse()
        errorResponse()
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, RegisterActivity::class.java)

        private const val MINIMUM_PASSWORD_LENGTH = 7
    }

    private val setTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            button_confirm_register.isEnabled =
                register_username_field.text.toString().isNotEmpty()
                        &&
                        register_password_field.text.toString().isNotEmpty()
                        &&
                        register_password_field.text.toString().length > MINIMUM_PASSWORD_LENGTH
        }
    }

    private fun setOnClickListenerEvent() {
        button_confirm_register
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.onContinueClicked(
                    register_username_field.text.toString().toLowerCase(Locale.ENGLISH),
                    register_password_field.text.toString(),
                    id = 0
                )
            }
    }

    private fun homeResponse() {
        viewModel.home
            .doOnIoObserveOnMain()
            .subscribeBy {
                openHome()
            }
            .addTo(compositeDisposable)
    }

    private fun errorResponse() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    ErrorType.ErrorRegistration -> {
                        Snackbar.make(
                            register_layout,
                            text_error_registration_failed,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    ErrorType.Unknown -> {
                        Snackbar.make(
                            register_layout,
                            text_unknown_error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun openHome() = startActivity(
        MainActivity.newIntent(this)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }
}