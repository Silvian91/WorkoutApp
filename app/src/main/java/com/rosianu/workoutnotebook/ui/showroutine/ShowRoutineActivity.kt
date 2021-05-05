package com.rosianu.workoutnotebook.ui.showroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.string.text_show_routines_toolbar
import com.rosianu.workoutnotebook.R.string.text_unknown_error
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineAdapter
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper
import com.rosianu.workoutnotebook.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_show_routine.*
import java.util.ArrayList

class ShowRoutineActivity : BaseActivity() {

    private lateinit var viewModel: ShowRoutineViewModel
    private lateinit var showRoutineAdapter: ShowRoutineAdapter
    private var routineData: ArrayList<ShowRoutineItemWrapper> = ArrayList()

    private fun showRoutineData(routineData: List<ShowRoutineItemWrapper>) {
        showRoutineAdapter.items = routineData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_show_routine)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(ShowRoutineViewModel::class.java)

        setToolbar()
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        viewModel.setWorkoutId(workoutId)
        initRoutineRecyclerView()
        viewModel.getTitle()
        titleResponse()
        viewModel.getRoutine()
        routineDataResponse()
        deleteWorkout()
        deleteDialog()
        showWorkoutResponse()
        onError()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(text_show_routines_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun titleResponse(){
        viewModel.workoutTitle
            .doOnIoObserveOnMain()
            .subscribeBy {
                viewModel.workoutTitle.value!!.forEach {
                    routineData.add(it)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun routineDataResponse() {
        viewModel.workoutData
            .doOnIoObserveOnMain()
            .subscribeBy {
                viewModel.workoutData.value!!.forEach {
                    routineData.add(it)
                }
                showRoutineData(routineData)
            }
            .addTo(compositeDisposable)
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

    private fun deleteWorkout() {
        button_delete_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.onDeleteClicked()
            }
    }

    private fun deleteDialog() {
        viewModel.delete
            .doOnIoObserveOnMain()
            .subscribeBy {
                showDeleteConfirmation(viewModel.delete.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun showDeleteConfirmation(workoutId: Long) {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_delete_routines)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> viewModel.onDeleteConfirmed(workoutId) }
            .show()
    }

    private fun showWorkoutResponse() {
        viewModel.showWorkout
            .doOnIoObserveOnMain()
            .subscribeBy {
                openShowWorkout()
            }
            .addTo(compositeDisposable)
    }


    private fun openShowWorkout() = startActivity(
        ShowWorkoutActivity.newIntent(
            this
        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    )

    private fun onError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                errorUnknown()
            }
            .addTo(compositeDisposable)
    }

    private fun errorUnknown() {
        Snackbar.make(upper_layout, text_unknown_error, LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        compositeDisposable.clear()

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