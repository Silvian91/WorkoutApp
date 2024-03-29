package com.rosianu.workoutnotebook.ui.copyworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.CopyWorkoutAdapter
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper
import com.rosianu.workoutnotebook.ui.editworkout.EditWorkoutActivity
import com.rosianu.workoutnotebook.ui.login.LoginActivity
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_copy_workout.*

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
        onContinueClicked()
        editWorkoutResponse()
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

    private fun onContinueClicked() {
        button_copy_routine
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.workoutResponse()
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

    private fun editWorkoutResponse() {
        viewModel.editWorkoutResponse
            .doOnIoObserveOnMain()
            .subscribeBy {
                openEditWorkout(viewModel.editWorkoutResponse.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun showLogin() = startActivity(
        LoginActivity.newIntent(this)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )

    private fun openEditWorkout(workoutId: Long) = startActivity(
        EditWorkoutActivity.newIntent(this, workoutId)
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