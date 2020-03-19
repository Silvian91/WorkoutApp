package com.example.workoutapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createLoginComponent().inject(this)
        setContentView(R.layout.activity_login)

        presenter.setView(this)
    }

    companion object{
        fun newIntent(context: Context) =
            Intent(context, LoginActivity::class.java)
    }
}