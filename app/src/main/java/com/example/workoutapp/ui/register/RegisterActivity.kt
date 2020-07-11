package com.example.workoutapp.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_error_registration_failed
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterContract.View {

    @Inject
    lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        setOnClickListenerEvent()
        presenter.setView(this)

        register_username_field.addTextChangedListener(setTextWatcher)
        register_password_field.addTextChangedListener(setTextWatcher)

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
                presenter.onContinueClicked(
                    register_username_field.text.toString().toLowerCase(Locale.ENGLISH),
                    register_password_field.text.toString(),
                    id = 0
                )
            }
    }

    override fun showHome() {
        startActivity(
            MainActivity.newIntent(this)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showError() {
        Snackbar.make(
            register_layout,
            text_error_registration_failed,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }
}