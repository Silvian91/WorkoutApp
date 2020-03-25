package com.example.workoutapp.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    @Inject
    lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createRegisterComponent().inject(this)
        setContentView(R.layout.activity_register)

        setOnClickListenerEvent()
        presenter.setView(this)
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, RegisterActivity::class.java)
    }

    private fun setOnClickListenerEvent() {
        button_confirm_register.setOnClickListener {
            presenter.onContinueClicked(
                register_username_field.text.toString(),
                register_password_field.text.toString()
            )
        }
    }

    override fun nextActivity() {
        startActivity(MainActivity.newIntent(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    override fun onDestroy() {
        button_confirm_register.setOnClickListener(null)
        presenter.finish()

        super.onDestroy()
    }
}