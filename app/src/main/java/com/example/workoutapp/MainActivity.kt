package com.example.workoutapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.addworkout.AddWorkoutActivity
import com.example.workoutapp.showworkout.ShowWorkoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar()
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

    override fun onDestroy() {
        add_workout.setOnClickListener(null)
        show_workout.setOnClickListener(null)

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
