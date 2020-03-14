package com.example.workoutapp.ui.mainactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        WorkoutApplication.get().components.createMainComponent().inject(this)

        setToolbar()
        presenter.setView(this)
        displayChuckNorrisQuote()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        add_workout.setOnClickListener { openAddWorkoutActivity() }
        show_workout.setOnClickListener { openShowWorkoutActivity() }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Workout App"
    }

    private fun openAddWorkoutActivity() {
        startActivity(AddWorkoutActivity.newIntent(this))
    }

    private fun openShowWorkoutActivity() {

        startActivity(ShowWorkoutActivity.newIntent(this))
    }

    private fun displayChuckNorrisQuote() {
        test_api.text = presenter.getChuckNorrisQuote().toString()
    }

    override fun onDestroy() {
        add_workout.setOnClickListener(null)
        show_workout.setOnClickListener(null)

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
