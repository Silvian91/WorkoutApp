package com.example.workoutnotebook.ui.copyworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.ui.copyworkout.adapter.CopyWorkoutAdapter
import com.example.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper
import com.example.workoutnotebook.ui.login.LoginActivity
import com.example.workoutnotebook.ui.showworkout.ShowWorkoutViewModel
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_copy_workout.*
import kotlinx.android.synthetic.main.activity_show_routine.*
import kotlinx.android.synthetic.main.activity_show_workout.*
import kotlinx.android.synthetic.main.view_holder_workouts.*

class CopyWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: CopyWorkoutViewModel

    private lateinit var copyWorkoutAdapter: CopyWorkoutAdapter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_copy_workout)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(CopyWorkoutViewModel::class.java)

        setToolbar()
        initCopyWorkoutRecyclerView()
        viewModel.getUser()
        getWorkouts()
        onLogin()
        onRadioButtonClicked(findViewById(R.id.activity_copy_workout))
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(R.string.text_show_workout_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun initCopyWorkoutRecyclerView() {
        copy_workout_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            copyWorkoutAdapter = CopyWorkoutAdapter()
            adapter = copyWorkoutAdapter
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            button_copy_routine.visibility = View.VISIBLE

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_workout ->
                    if (checked) {
                        // Pirates are the best
                    }
            }
        }
    }

    private fun getWorkouts() {
        viewModel.getWorkoutList
            .doOnIoObserveOnMain()
            .subscribeBy {
                showData(viewModel.getWorkoutList.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun showData(workoutsList: List<WorkoutItemWrapper>) {
        copyWorkoutAdapter.setData(workoutsList)
    }

    private fun onLogin() {
        viewModel.login
            .doOnIoObserveOnMain()
            .subscribeBy {
                showLogin()
            }
            .addTo(compositeDisposable)
    }

    private fun showLogin() = startActivity(
        LoginActivity.newIntent(this)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )

    companion object {
        fun newIntent(context: Context) =
            Intent(context, CopyWorkoutActivity::class.java)
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

}