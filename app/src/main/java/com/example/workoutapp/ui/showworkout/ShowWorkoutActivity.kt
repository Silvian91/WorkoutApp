package com.example.workoutapp.ui.showworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.main.HomeActivity
import com.example.workoutapp.ui.showroutine.ShowRoutineActivity
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_show_workout.*
import kotlinx.android.synthetic.main.view_holder_workouts.*
import javax.inject.Inject

class ShowWorkoutActivity : AppCompatActivity(), ShowWorkoutContract.View {

    @Inject
    lateinit var presenter: ShowWorkoutContract.Presenter

    private lateinit var showWorkoutAdapter: ShowWorkoutRecyclerAdapter

    override fun showWorkoutsListData(workoutsList: List<ShowWorkoutItemWrapper>) {
        showWorkoutAdapter.setData(workoutsList)
    }

    override fun showRoutines(workoutId: Long) {
        startActivity(ShowRoutineActivity.newIntent(this, workoutId))
    }

    override fun showEmptyScreen() {
        Toast.makeText(this, "Show Empty Screen", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this, "Show Error", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        startActivity(HomeActivity.newIntent(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
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
            title = "Workouts"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun initWorkoutRecyclerView() {
        recyclerViewWorkout.apply {
            layoutManager = LinearLayoutManager(this@ShowWorkoutActivity)
            showWorkoutAdapter = ShowWorkoutRecyclerAdapter(presenter)
            adapter = showWorkoutAdapter
        }
    }

    override fun alertDialog(workoutId: Long, workoutsList: List<ShowWorkoutItemWrapper>) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setMessage(R.string.text_dialog_delete_routines)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> deleteSnackbar(workoutId, workoutsList) }
            .show()
    }

    override fun deleteSnackbar(workoutId: Long, workoutsList: List<ShowWorkoutItemWrapper>) {
        Snackbar.make(
            workouts_view_holder, "CARD DELETED",
            Snackbar.LENGTH_LONG
        ).setAction("UNDO", View.OnClickListener {})
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    if (event == DISMISS_EVENT_TIMEOUT) {
                        presenter.onDeleteClicked(workoutId, workoutsList)
                    }
                }
            })
            .show()
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ShowWorkoutActivity::class.java)
    }


}