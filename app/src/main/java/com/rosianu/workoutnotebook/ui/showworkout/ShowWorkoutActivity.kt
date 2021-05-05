package com.rosianu.workoutnotebook.ui.showworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.string.*
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.ui.login.LoginActivity
import com.rosianu.workoutnotebook.ui.showroutine.ShowRoutineActivity
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutAdapter
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutAdapter.WorkoutClickListener
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutAdapter.WorkoutDeleteClickListener
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_show_workout.*
import kotlinx.android.synthetic.main.view_holder_workouts.*

class ShowWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: ShowWorkoutViewModel

    private lateinit var workoutAdapter: ShowWorkoutAdapter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_workout)

        viewModel = ViewModelProvider(
            this,viewModelFactory.get()
        ).get(ShowWorkoutViewModel::class.java)

        setToolbar()
        initWorkoutRecyclerView()
        viewModel.getUser()
        getWorkouts()
        onError()
        onLogin()
        onShowRoutines()
        onDelete()
        onUndo()
        onUndoDelete()
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
                ShowWorkoutAdapter(
                    WorkoutClickListener { workoutId -> viewModel.onWorkoutClicked(workoutId) },
                    WorkoutDeleteClickListener { workoutId -> viewModel.onDeleteWorkout(workoutId) },
                    this@ShowWorkoutActivity.lifecycle
                )
            adapter = workoutAdapter
        }
    }

    private fun showWorkouts(workoutsList: List<ShowWorkoutItemWrapper>) {
        workoutAdapter.setData(workoutsList)
    }

    private fun getWorkouts() {
        viewModel.getWorkoutList
            .doOnIoObserveOnMain()
            .subscribeBy {
                showWorkouts(viewModel.getWorkoutList.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun onError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                showError()
            }
            .addTo(compositeDisposable)
    }

    private fun onLogin() {
        viewModel.login
            .doOnIoObserveOnMain()
            .subscribeBy {
                showLogin()
            }
            .addTo(compositeDisposable)
    }

    private fun onShowRoutines() {
        viewModel.showRoutinesResponse
            .doOnIoObserveOnMain()
            .subscribeBy {
                showRoutines(viewModel.showRoutinesResponse.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun onDelete() {
        viewModel.deleteWorkoutResponse
            .doOnIoObserveOnMain()
            .subscribeBy {
                showDeleteConfirmation(viewModel.deleteWorkoutResponse.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun showError() {
        Snackbar.make(
            workouts_view_holder,
            text_unknown_error,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(getString(text_snackbar_retry)) { viewModel.onRetryClicked() }
            .show()
    }

    private fun showLogin() = startActivity(
        LoginActivity.newIntent(this)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )

    private fun showDeleteConfirmation(workoutId: Long) {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_delete_routines)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> reloadActivity() }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> viewModel.softDeleteWorkout(workoutId) }
            .show()
    }

    private fun onUndo() {
        viewModel.undoOption
            .doOnIoObserveOnMain()
            .subscribeBy {
                showUndoOption(viewModel.undoOption.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun showUndoOption(workoutId: Long) {
        Snackbar.make(
            show_workout_activity, getString(text_snack_deletion_confirmed),
            Snackbar.LENGTH_LONG
        ).setAction(getString(text_snackbar_undo)) { viewModel.onUndoDeletion(workoutId) }
            .show()
    }

    private fun onUndoDelete() {
        viewModel.undoDelete
            .doOnIoObserveOnMain()
            .subscribeBy {
                reloadActivity()
            }
            .addTo(compositeDisposable)
    }


    private fun showRoutines(workoutId: Long) = startActivity(
        ShowRoutineActivity.newIntent(
            this, workoutId
        )
    )

    private fun reloadActivity() {
        finish()
        startActivity(intent)
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ShowWorkoutActivity::class.java)
    }

}