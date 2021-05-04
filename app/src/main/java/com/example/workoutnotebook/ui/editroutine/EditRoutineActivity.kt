package com.example.workoutnotebook.ui.editroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.ui.editroutine.adapter.EditRoutineAdapter
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_edit_routine.*
import java.util.*

class EditRoutineActivity : BaseActivity() {

    private lateinit var viewModel: EditRoutineViewModel
    private var editRoutineAdapter: EditRoutineAdapter = EditRoutineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_routine)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(EditRoutineViewModel::class.java)

        val editWorkoutId = intent.getLongExtra(editedWorkoutIdExtra, 0)
        val workoutId = intent.getLongExtra(workoutIdExtra, 0)
        observeViewModel()
        viewModel.initData(editWorkoutId)
        initRoutineRecyclerView()
    }

    private fun initRoutineRecyclerView() {
        edit_routine_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@EditRoutineActivity)
            adapter = editRoutineAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.routinesData
            .doOnIoObserveOnMain()
            .subscribeBy {
                editRoutineAdapter.items = it
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
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