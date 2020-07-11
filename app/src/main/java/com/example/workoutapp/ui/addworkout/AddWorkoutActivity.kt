package com.example.workoutapp.ui.addworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_add_workout_toolbar
import com.example.workoutapp.R.string.text_unknown_error
import com.example.workoutapp.ui.addroutine.AddRoutineActivity
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_add_workout.*
import javax.inject.Inject

class AddWorkoutActivity : BaseActivity(), AddWorkoutContract.View {

    @Inject
    lateinit var presenter: AddWorkoutContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_workout)

        setToolbar()
        presenter.start()
        presenter.setView(this)
        button_confirm_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                presenter.onConfirmClicked(workout_title_field.text.toString())
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

    override fun showAddRoutine(workoutId: Long) {
        startActivity(
            AddRoutineActivity.newIntent(
                this,
                workoutId
            ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun errorUnknown() {
        Snackbar.make(add_workout_activity, text_unknown_error, Snackbar.LENGTH_SHORT)
    }

    override fun showLogin() {
        startActivity(
            LoginActivity.newIntent(this)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showError() {
        workout_title_field.error = getString(R.string.text_toast_add_workout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        finish()
        return true
    }

    //ALWAYS CALL METHODS BEFORE SUPER.ONDESTROY BECAUSE SUPER SHOULD BE THE LAST THING THAT RUNS
    override fun onDestroy() {
        presenter.finish()

        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, AddWorkoutActivity::class.java)
    }

}