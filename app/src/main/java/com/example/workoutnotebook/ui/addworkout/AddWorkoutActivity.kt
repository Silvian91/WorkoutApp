package com.example.workoutnotebook.ui.addworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.ViewModelProvider
import com.example.workoutnotebook.R
import com.example.workoutnotebook.R.string.text_add_workout_toolbar
import com.example.workoutnotebook.R.string.text_unknown_error
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.ui.addroutine.AddRoutineActivity
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add_workout.*
import com.example.core.ui.BaseActivity as BaseActivity

class AddWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: AddWorkoutViewModel

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
                viewModel.onConfirmClicked(workout_title_field.text.toString())
            }
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

    private fun openAddRoutine(workoutId: Long) = startActivity(
            AddRoutineActivity.newIntent(
                this,
                workoutId
            ).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
        )

    private fun onError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    ErrorType.Unknown -> {
                        errorUnknown()
                    }
                    ErrorType.ErrorWorkoutName -> {
                        errorTitle()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun errorUnknown() {
        Snackbar.make(add_workout_activity, text_unknown_error, Snackbar.LENGTH_SHORT)
    }

    private fun errorTitle() {
        workout_title_field.error = getString(R.string.text_toast_add_workout)
    }

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