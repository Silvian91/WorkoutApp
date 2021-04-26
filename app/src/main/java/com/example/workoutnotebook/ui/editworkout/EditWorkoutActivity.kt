package com.example.workoutnotebook.ui.editworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R.layout.activity_edit_workout
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_edit_workout.*

class EditWorkoutActivity : BaseActivity() {

    private lateinit var viewModel: EditWorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_workout)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditWorkoutViewModel::class.java)

        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        viewModel.setWorkoutId(workoutId)
        viewModel.getTitle()
        titleResponse()
    }

    private fun titleResponse(){
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