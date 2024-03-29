package com.rosianu.workoutnotebook.ui.addroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.ViewModelProvider
import com.rosianu.core.ui.BaseActivity
import com.rosianu.core.ui.error.ErrorType
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.string.text_add_routine_toolbar
import com.rosianu.workoutnotebook.R.string.text_unknown_error
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add_routine.*

class AddRoutineActivity : BaseActivity() {

    private lateinit var viewModel: AddRoutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_routine)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(AddRoutineViewModel::class.java)

        setToolbar()
        viewModel.getUser()
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        viewModel.setWorkoutId(workoutId)

        setOnClickListenerEvent()
        setPreviousInvisible()
        listenForSave()
        listenForNext()
        listenForPrevious()
        showError()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(text_add_routine_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun setOnClickListenerEvent() {
        button_next_routine
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                viewModel.onNextClicked(
                    routine_name.text.toString(),
                    routine_sets.text.toString(),
                    routine_reps.text.toString(),
                    routine_weight.text.toString(),
                    routine_rest.text.toString()
                )
            }
        button_save_routine
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                viewModel.onSaveClicked(
                    routine_name.text.toString(),
                    routine_sets.text.toString(),
                    routine_reps.text.toString(),
                    routine_weight.text.toString(),
                    routine_rest.text.toString()
                )
            }

        button_previous_routine
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe { viewModel.onPreviousClicked() }

    }

    private fun setPreviousVisible() {
        button_previous_routine.visibility = View.VISIBLE
    }

    private fun setPreviousInvisible() {
        button_previous_routine.visibility = View.INVISIBLE
    }

    private fun listenForSave() {
        viewModel.saveClicked
            .doOnIoObserveOnMain()
            .subscribeBy { saveRoutines() }
            .addTo(compositeDisposable)
    }

    private fun listenForNext() {
        viewModel.nextClicked
            .doOnIoObserveOnMain()
            .subscribeBy {
                clearAllInputFields()
                resetFocus()
                setPreviousVisible()
            }
            .addTo(compositeDisposable)
    }

    private fun listenForPrevious() {
        viewModel.previousClicked
            .doOnIoObserveOnMain()
            .subscribeBy {
                routine_name.setText(it.routineName)
                routine_sets.setText(it.sets)
                routine_reps.setText(it.reps)
                routine_weight.setText(it.weight)
                routine_rest.setText(it.rest)
                resetFocus()
                viewModel.checkPreviousVisible()
                checkVisible()
                viewModel.removeLastIndex()
            }
            .addTo(compositeDisposable)
    }

    private fun checkVisible() {
        if (viewModel.previousNotVisible.value!!) {
            viewModel.previousNotVisible
                .doOnIoObserveOnMain()
                .subscribeBy { button_previous_routine.visibility = View.INVISIBLE }
                .addTo(compositeDisposable)
        } else {
            viewModel.previousVisible
                .doOnIoObserveOnMain()
                .subscribeBy {
                    button_previous_routine.visibility = View.VISIBLE
                }
                .addTo(compositeDisposable)
        }
    }

    private fun openMain() {
        viewModel.routines
            .doOnIoObserveOnMain()
            .subscribeBy { nextActivity() }
            .addTo(compositeDisposable)
    }

    private fun saveRoutines() = openMain()

    private fun clearAllInputFields() {
        routine_name.setText("")
        routine_sets.setText("")
        routine_reps.setText("")
        routine_weight.setText("")
        routine_rest.setText("")
    }

    private fun resetFocus() = routine_name.requestFocus()

    private fun nextActivity() = startActivity(
        MainActivity.newIntent(
            this
        ).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    )

    private fun errorFieldEmpty(): String = getString(R.string.text_field_cannotEmpty)

    private fun showError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    ErrorType.ErrorNameEmpty -> {
                        routine_name.requestFocus()
                        routine_name.error = errorFieldEmpty()
                    }
                    ErrorType.ErrorSetsEmpty -> {
                        routine_sets.requestFocus()
                        routine_sets.error = errorFieldEmpty()
                    }
                    ErrorType.ErrorRepsEmpty -> {
                        routine_reps.requestFocus()
                        routine_reps.error = errorFieldEmpty()
                    }
                    ErrorType.ErrorWeightEmpty -> {
                        routine_weight.requestFocus()
                        routine_weight.error = errorFieldEmpty()
                    }
                    ErrorType.ErrorRestEmpty -> {
                        routine_rest.requestFocus()
                        routine_rest.error = errorFieldEmpty()
                    }
                    ErrorType.Unknown -> {
                        Snackbar.make(
                            add_routine_activity,
                            text_unknown_error,
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_delete_routines_back_button)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ ->
                finish()
                viewModel.onBackClicked(
                    routine_name.text.toString(),
                    routine_sets.text.toString(),
                    routine_reps.text.toString(),
                    routine_weight.text.toString(),
                    routine_rest.text.toString()
                )
            }
            .show()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    companion object {
        const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, AddRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }
}