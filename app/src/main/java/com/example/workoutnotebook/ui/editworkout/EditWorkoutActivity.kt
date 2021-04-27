package com.example.workoutnotebook.ui.editworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R
import com.example.workoutnotebook.R.layout.activity_edit_workout
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_edit_workout.*

class EditWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: EditWorkoutViewModel
    private lateinit var stringToCompare: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_workout)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditWorkoutViewModel::class.java)

        setToolbar()
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        viewModel.setWorkoutId(workoutId)
        viewModel.getTitle()
        titleResponse()
        confirmEditClickListener()
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
                    stringToCompare = it
                }
            }
            .addTo(compositeDisposable)
    }

    private fun displayTitle(title: String) {
        edit_title_field.setText(title)
        edit_title_field.setSelection(title.length)
    }

    private fun confirmEditClickListener() {
        button_confirm_edit
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                checkForSameTitle()
            }
    }

    private fun checkForSameTitle() {
        if (edit_title_field.text.toString() == stringToCompare) {
            showDialog()
        } else {
            openEditRoutine()
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.text_dialog_edit_workout)
            .setNegativeButton(
                R.string.text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                R.string.text_dialog_alert_confirm
            ) { _, _ -> openEditRoutine() }
            .show()
    }

    private fun openEditRoutine() {

    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

    companion object {
        const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, EditWorkoutActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }

}