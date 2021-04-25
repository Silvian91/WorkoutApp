package com.example.workoutnotebook.ui.editroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R.layout.activity_edit_routine

class EditRoutineActivity : BaseActivity() {

    private lateinit var viewModel: EditRoutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_routine)

    }

    companion object {
        private const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, EditRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }
}