package com.rosianu.workoutnotebook.ui.addworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.ViewModelProvider
import com.rosianu.core.ui.BaseActivity
import com.rosianu.core.ui.error.ErrorType
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.string.text_add_workout_toolbar
import com.rosianu.workoutnotebook.R.string.text_unknown_error
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.ui.addroutine.AddRoutineActivity
import com.rosianu.workoutnotebook.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add_workout.*

class AddWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: AddWorkoutViewModel
    private var isSame: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_workout)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(AddWorkoutViewModel::class.java)

        setToolbar()
        viewModel.getUser()
        onUserUnauthorized()
        saveWorkoutId()
        button_confirm_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                checkForExistingWorkout()
            }
        showError()
    }

    private fun setToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            title = getString(text_add_workout_toolbar)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }
    }

    private fun onUserUnauthorized() {
        viewModel.userUnauthorized
            .doOnIoObserveOnMain()
            .subscribeBy {
                openLogin()
            }
            .addTo(compositeDisposable)
    }

    private fun saveWorkoutId() {
        viewModel.workout
            .doOnIoObserveOnMain()
            .subscribeBy {
                openAddRoutine(viewModel.workout.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun openLogin() = startActivity(
        LoginActivity.newIntent(
            this
        ).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    )

    private fun checkForExistingWorkout(){
        if (viewModel.workoutsListCompare.value == null) {
            viewModel.onConfirmClicked(workout_title_field.text.toString())
        } else {
            checkForExistingWorkoutTitle()
        }
    }

    private fun checkForExistingWorkoutTitle() {
        viewModel.workoutsListCompare.value!!.forEach {
            if (
                workout_title_field.text.toString() == it
            ) {
                isSame = true
            }
        }
        if (isSame) {
            showDialog()
        } else {
            viewModel.onConfirmClicked(workout_title_field.text.toString())
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
            ) { _, _ -> viewModel.onConfirmClicked(workout_title_field.text.toString()) }
            .show()
    }

    private fun openAddRoutine(workoutId: Long) = startActivity(
        AddRoutineActivity.newIntent(
            this,
            workoutId
        ).addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    )

    private fun showError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    ErrorType.Unknown -> {
                        errorUnknown()
                    }
                    ErrorType.ErrorWorkoutName -> {
                        workout_title_field.requestFocus()
                        workout_title_field.error = errorEmptyTitle()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun errorUnknown() {
        Snackbar.make(add_workout_activity, text_unknown_error, Snackbar.LENGTH_SHORT)
    }

    private fun errorEmptyTitle(): String = getString(R.string.text_error_add_workout_empty_title)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, AddWorkoutActivity::class.java)
    }

}