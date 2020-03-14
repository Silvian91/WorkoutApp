package com.example.workoutapp.ui.addworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.addroutine.AddRoutineActivity
import kotlinx.android.synthetic.main.activity_add_workout.*
import javax.inject.Inject

class AddWorkoutActivity : AppCompatActivity(), AddWorkoutContract.View {

    @Inject
    lateinit var presenter: AddWorkoutContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_workout)
        WorkoutApplication.get().components.createAddWorkoutComponent().inject(this)

        setToolbar()
        presenter.setView(this)
        button_confirm_workout.setOnClickListener {
            presenter.onConfirmClicked(workout_title_field.text.toString())
        }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = "Add Workout"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    override fun showAddRoutine(workoutId: Long) {
        startActivity(
            AddRoutineActivity.newIntent(
                this,
                workoutId
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showError() {
        workout_title_field.error = getString(R.string.text_toast_add_workout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    //ALWAYS CALL METHODS BEFORE SUPER.ONDESTROY BECAUSE SUPER SHOULD BE THE LAST THING THAT RUNS
    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, AddWorkoutActivity::class.java)
    }

}