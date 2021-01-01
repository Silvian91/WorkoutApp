package com.example.workoutnotebook.ui.addroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.ViewModelProvider
import com.example.workoutnotebook.R
import com.example.workoutnotebook.R.string.text_add_routine_toolbar
import com.example.workoutnotebook.R.string.text_unknown_error
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.core.ui.BaseActivity
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.ui.main.MainActivity
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
        listenForFinish()
        listenForContinue()
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
        button_confirm_routine
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                viewModel.onContinueClicked(
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
                viewModel.onFinishClicked(
                    routine_name.text.toString(),
                    routine_sets.text.toString(),
                    routine_reps.text.toString(),
                    routine_weight.text.toString(),
                    routine_rest.text.toString()
                )
            }

    }

    private fun listenForFinish() {
        viewModel.finishClicked
            .doOnIoObserveOnMain()
            .subscribeBy {
                saveRoutines()
            }
            .addTo(compositeDisposable)
    }

    private fun listenForContinue() {
        viewModel.continueClicked
            .doOnIoObserveOnMain()
            .subscribeBy {
                clearAllInputFields()
                resetFocus()
            }
            .addTo(compositeDisposable)
    }

    private fun openMain() {
        viewModel.routines
            .doOnIoObserveOnMain()
            .subscribeBy {
                nextActivity()
            }
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
        private val DROP_DOWN_LIST = arrayOf("kg", "lbs")

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, AddRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }
}