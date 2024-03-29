package com.rosianu.workoutnotebook.ui.editworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.rosianu.core.ui.BaseActivity
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.layout.activity_edit_workout
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.ui.editroutine.EditRoutineActivity
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_edit_workout.*

class EditWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: EditWorkoutViewModel
    private var isSame: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_workout)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditWorkoutViewModel::class.java)

        setToolbar()
        val editWorkoutId = intent.getLongExtra(editWorkoutIdExtra, 0)
        viewModel.getUser()
        viewModel.setWorkoutId(editWorkoutId)
        viewModel.getTitle()
        titleResponse()
        saveWorkoutId()
        confirmEditClicked()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(R.string.text_edit_workout_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun titleResponse() {
        viewModel.workoutTitle
            .doOnIoObserveOnMain()
            .subscribeBy {
                viewModel.workoutTitle.value!!.forEach {
                    displayTitle(it)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun displayTitle(title: String) {
        edit_title_field.setText(title)
        edit_title_field.setSelection(title.length)
    }

    private fun confirmEditClicked() {
        button_confirm_edit
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                checkForExistingWorkoutTitle()
            }
    }

    private fun checkForExistingWorkoutTitle() {
        viewModel.workoutsListCompare.value!!.forEach {
            if (
                edit_title_field.text.toString() == it
            ) {
                isSame = true
            }
        }
        if (isSame) {
            showDialog()
        } else {
            viewModel.onConfirmClicked(edit_title_field.text.toString())
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_edit_workout)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> isSame = false }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> viewModel.onConfirmClicked(edit_title_field.text.toString()) }
            .show()
    }

    private fun saveWorkoutId() {
        viewModel.newWorkoutId
            .doOnIoObserveOnMain()
            .subscribeBy { newWorkoutId ->
                val editWorkoutId = viewModel.getEditWorkoutId()
                openEditRoutine(editWorkoutId, newWorkoutId)
            }
            .addTo(compositeDisposable)
    }

    private fun openEditRoutine(editWorkoutId: Long, newWorkoutId: Long) {
        startActivity(
            EditRoutineActivity.newIntent(
                this,
                editWorkoutId,
                newWorkoutId
            )
        )
        finish()
    }


    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    companion object {
        const val editWorkoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, EditWorkoutActivity::class.java).apply {
                putExtra(editWorkoutIdExtra, workoutId)
            }
    }

}