package com.example.workoutapp.ui.showroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_show_routines_toolbar
import com.example.workoutapp.R.string.text_unknown_error
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.main.MainActivity
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineAdapter
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_show_routine.*
import javax.inject.Inject

class ShowRoutineActivity : AppCompatActivity(), ShowRoutineContract.View {

    @Inject
    lateinit var presenter: ShowRoutineContract.Presenter

    private lateinit var showRoutineAdapter: ShowRoutineAdapter

    override fun showRoutineData(routineData: List<ShowRoutineItemWrapper>) {
        showRoutineAdapter.setData(routineData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show_routine)
        WorkoutApplication.get().components.createShowRoutineComponent().inject(this)

        setToolbar()
        presenter.setView(this)
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        presenter.setWorkoutId(workoutId)
        initRoutineRecyclerView()
        presenter.start()

        deleteWorkout()
    }

    private fun deleteWorkout() {
        button_delete_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                presenter.onDeleteClicked()
            }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(text_show_routines_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    private fun initRoutineRecyclerView() {
        recyclerViewRoutine.apply {
            layoutManager = LinearLayoutManager(this@ShowRoutineActivity)
            showRoutineAdapter = ShowRoutineAdapter()
            adapter = showRoutineAdapter
        }
    }

    override fun openShowWorkoutActivity() {
        startActivity(ShowWorkoutActivity.newIntent(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    override fun errorUnknown() {
        Snackbar.make(upper_layout, text_unknown_error, LENGTH_SHORT).show()
    }

    override fun showDeleteAlertDialog(workoutId: Long) {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_delete_routines)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> presenter.onDeleteConfirmed(workoutId) }
            .show()
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, ShowRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }

}