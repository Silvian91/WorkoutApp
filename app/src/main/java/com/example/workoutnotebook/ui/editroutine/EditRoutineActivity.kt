package com.example.workoutnotebook.ui.editroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R.layout.activity_edit_routine
import com.example.workoutnotebook.ui.editworkout.EditWorkoutActivity.Companion.editWorkoutIdExtra

class EditRoutineActivity : BaseActivity() {

    private lateinit var viewModel: EditRoutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_routine)

        val editWorkoutId = intent.getLongExtra(editWorkoutIdExtra, 0)
        val workoutIdExtra = intent.getLongExtra(workoutIdExtra, 0)

    }

    companion object {
        private const val workoutIdExtra = "workoutId"
        private const val editedWorkoutIdExtra = "editedWorkoutId"

        fun newIntent(context: Context, editedWorkoutId: Long, workoutId: Long) =
            Intent(context, EditRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
                putExtra(editedWorkoutIdExtra, editedWorkoutId)
            }
    }
}