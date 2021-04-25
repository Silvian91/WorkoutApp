package com.example.workoutnotebook.ui.editworkout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R.layout.activity_edit_workout
import com.example.workoutnotebook.ui.editworkout.adapter.EditWorkoutAdapter

class EditWorkoutActivity: BaseActivity() {

    private lateinit var viewModel: EditWorkoutViewModel

    private lateinit var editWorkoutAdapter: EditWorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_workout)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditWorkoutViewModel::class.java)


    }

    companion object {
        private const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, EditWorkoutActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }

}