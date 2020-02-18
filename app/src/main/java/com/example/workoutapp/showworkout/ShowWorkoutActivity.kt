package com.example.workoutapp.showworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.MainActivity
import com.example.workoutapp.R
import com.example.workoutapp.WorkoutApplication
import com.example.workoutapp.showroutine.ShowRoutineActivity
import com.example.workoutapp.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.showworkout.adapter.ShowWorkoutRecyclerAdapter
import kotlinx.android.synthetic.main.activity_show_workout.*
import javax.inject.Inject

class ShowWorkoutActivity : AppCompatActivity(), ShowWorkoutContract.View {

    @Inject
    lateinit var presenter: ShowWorkoutContract.Presenter

    private lateinit var showWorkoutAdapter: ShowWorkoutRecyclerAdapter

    override fun showWorkoutListData(workoutList: List<ShowWorkoutItemWrapper>) {
        showWorkoutAdapter.setData(workoutList)
    }

    override fun showRoutines(workoutId: Long) {
        startActivity(ShowRoutineActivity.newIntent(this, workoutId))
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        startActivity(MainActivity.newIntent(this).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        return true
    }

    private fun initWorkoutRecyclerView() {
        recyclerViewWorkout.apply {
            layoutManager = LinearLayoutManager(this@ShowWorkoutActivity)
            showWorkoutAdapter = ShowWorkoutRecyclerAdapter(presenter)
            adapter = showWorkoutAdapter
        }
    }

    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ShowWorkoutActivity::class.java)
    }


}