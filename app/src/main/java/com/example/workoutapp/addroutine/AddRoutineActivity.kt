package com.example.workoutapp.addroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.MainActivity
import com.example.workoutapp.R
import com.example.workoutapp.WorkoutApplication
import com.example.workoutapp.addroutine.AddRoutineContract.ErrorType.*
import kotlinx.android.synthetic.main.activity_add_routine.*
import javax.inject.Inject

class AddRoutineActivity : AppCompatActivity(), AddRoutineContract.View {

    @Inject
    lateinit var presenter: AddRoutineContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_routine)
        WorkoutApplication.get().components.createAddRoutineComponent().inject(this)

        setToolbar()
        presenter.setView(this)
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        presenter.setWorkoutId(workoutId)
        setOnClickListenerEvent()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = "Add Routine"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun setOnClickListenerEvent() {
        button_confirm_routine.setOnClickListener {
            presenter.onContinueClicked(
                routine_name.text.toString(),
                routine_sets.text.toString(),
                routine_reps.text.toString(),
                routine_weight.text.toString(),
                routine_rest.text.toString()
            )
        }
        button_save_routine.setOnClickListener {
            presenter.onFinishClicked(
                routine_name.text.toString(),
                routine_sets.text.toString(),
                routine_reps.text.toString(),
                routine_weight.text.toString(),
                routine_rest.text.toString()
            )
        }

    }

    override fun clearAllInputFields() {
        routine_name.setText("")
        routine_sets.setText("")
        routine_reps.setText("")
        routine_weight.setText("")
        routine_rest.setText("")
    }

    override fun resetFocus() {
        routine_name.requestFocus()
    }

    override fun nextActivity() {
        startActivity(MainActivity.newIntent(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    override fun errorFieldEmpty(): String {
        return getString(R.string.text_field_cannotEmpty)
    }

    override fun showError(errorType: AddRoutineContract.ErrorType) {
        when (errorType) {
            NAME_EMPTY -> {
                routine_name.requestFocus()
                routine_name.error = errorFieldEmpty()
            }
            SETS_EMPTY -> {
                routine_sets.requestFocus()
                routine_sets.error = errorFieldEmpty()
            }
            REPS_EMPTY -> {
                routine_reps.requestFocus()
                routine_reps.error = errorFieldEmpty()
            }
            WEIGHT_EMPTY -> {
                routine_weight.requestFocus()
                routine_weight.error = errorFieldEmpty()
            }
            REST_EMPTY -> {
                routine_rest.requestFocus()
                routine_rest.error = errorFieldEmpty()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        presenter.onBackClicked(
            routine_name.text.toString(),
            routine_sets.text.toString(),
            routine_reps.text.toString(),
            routine_weight.text.toString(),
            routine_rest.text.toString()
        )

        return true
    }


    override fun onDestroy() {
        button_confirm_routine.setOnClickListener(null)
        button_save_routine.setOnClickListener(null)
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long): Intent {
            val intent = Intent(context, AddRoutineActivity::class.java)
            intent.putExtra(workoutIdExtra, workoutId)
            return intent
        }
    }
}