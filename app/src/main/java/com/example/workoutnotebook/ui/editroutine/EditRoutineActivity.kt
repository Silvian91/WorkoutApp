package com.example.workoutnotebook.ui.editroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R.layout.activity_edit_routine
import com.example.workoutnotebook.ui.editroutine.adapter.EditRoutineAdapter
import com.example.workoutnotebook.ui.showroutine.ShowRoutineActivity

class EditRoutineActivity: BaseActivity() {

    private lateinit var viewModel: EditRoutineViewModel

    private lateinit var editRoutineAdapter: EditRoutineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_edit_routine)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditRoutineViewModel::class.java)


    }

    companion object {
        private const val workoutIdExtra = "workoutId"

        fun newIntent(context: Context, workoutId: Long) =
            Intent(context, EditRoutineActivity::class.java).apply {
                putExtra(workoutIdExtra, workoutId)
            }
    }

}