package com.example.workoutapp.ui.registeractivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    @Inject
    lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WorkoutApplication.get().components.createRegisterComponent().inject(this)
        setContentView(R.layout.activity_register)

        presenter.setView(this)
    }

    companion object{
        fun newIntent(context: Context) =
            Intent(context, RegisterActivity::class.java)
    }
}