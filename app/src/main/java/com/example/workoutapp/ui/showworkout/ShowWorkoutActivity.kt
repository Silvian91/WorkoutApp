package com.example.workoutapp.ui.showworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.string.*
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.showroutine.ShowRoutineActivity
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity.Companion.newIntent
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutAdapter
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_show_workout.*
import kotlinx.android.synthetic.main.view_holder_workouts.*
import javax.inject.Inject

class ShowWorkoutActivity : BaseActivity(), ShowWorkoutContract.View {

    @Inject
    lateinit var presenter: ShowWorkoutContract.Presenter

    private lateinit var workoutAdapter: ShowWorkoutAdapter

    override fun showWorkouts(workoutsList: List<ShowWorkoutItemWrapper>) {
        workoutAdapter.setData(workoutsList)
    }

    override fun showRoutines(workoutId: Long) {
        startActivity(ShowRoutineActivity.newIntent(this, workoutId))
    }

    override fun showError() {
        Snackbar.make(
            workouts_view_holder,
            text_unknown_error,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(text_snackbar_retry)) {}
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        presenter.onRetryClicked()
                    }
                }
            })
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_workout)
        WorkoutApplication.get().components.createShowWorkoutComponent().inject(this)

        setToolbar()
        presenter.setView(this)
        initWorkoutRecyclerView()
        presenter.start()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(text_show_workout_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun initWorkoutRecyclerView() {
        recyclerViewWorkout.apply {
            layoutManager = LinearLayoutManager(this@ShowWorkoutActivity)
            workoutAdapter =
                ShowWorkoutAdapter(presenter, this@ShowWorkoutActivity.lifecycle)
            adapter = workoutAdapter
        }
    }

    override fun showDeleteConfirmation(workoutId: Long) {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_delete_routines)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> presenter.onUndoDeletion(workoutId) }
            .show()
    }

    override fun showUndoOption(workoutId: Long) {
        Snackbar.make(
            workouts_view_holder, getString(text_snack_deletion_confirmed),
            Snackbar.LENGTH_SHORT
        ).setAction(getString(R.string.text_snackbar_undo)) {}
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        presenter.onDeleteClicked(workoutId)
                    }
                }
            })
            .show()
    }

    override fun showLogin() {
        startActivity(
            LoginActivity.newIntent(this)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun retryView() {
        finish()
        startActivity(newIntent(this@ShowWorkoutActivity))
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ShowWorkoutActivity::class.java)
    }

}