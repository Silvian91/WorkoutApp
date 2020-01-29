package com.example.workoutapp.showroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.MainActivity
import com.example.workoutapp.R
import com.example.workoutapp.WorkoutApplication
import com.example.workoutapp.model.routine.RoutineEntity
import com.example.workoutapp.showroutine.adapter.ShowRoutineRecyclerAdapter
import com.example.workoutapp.showworkout.ShowWorkoutActivity
import kotlinx.android.synthetic.main.activity_show_routine.*
import javax.inject.Inject

class ShowRoutineActivity : AppCompatActivity(), ShowRoutineContract.View {

    @Inject
    lateinit var presenter: ShowRoutineContract.Presenter

    private lateinit var showRoutineAdapter: ShowRoutineRecyclerAdapter

    override fun showRoutineData(routineData: List<RoutineEntity>) {
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

        button_delete_workout.setOnClickListener {
            AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setMessage(R.string.text_dialog_delete_routines)
                .setNegativeButton(
                    R.string.text_dialog_delete_routines_cancel
                ) { _, _ -> }
                .setPositiveButton(
                    R.string.text_dialog_delete_routines_confirm
                ) { _, _ -> presenter.onDeleteClicked(workoutId) }
                .show()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = "Routines"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        startActivity(MainActivity.newIntent(this))
        return true
    }

    private fun initRoutineRecyclerView() {
        recyclerViewRoutine.apply {
            layoutManager = LinearLayoutManager(this@ShowRoutineActivity)
            showRoutineAdapter = ShowRoutineRecyclerAdapter()
            adapter = showRoutineAdapter
        }
    }

    override fun nextActivity() {
        startActivity(ShowWorkoutActivity.newIntent(this))
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long): Intent {
            val intent = Intent(context, ShowRoutineActivity::class.java)
            intent.putExtra(workoutIdExtra, workoutId)
            return intent
        }
    }

}