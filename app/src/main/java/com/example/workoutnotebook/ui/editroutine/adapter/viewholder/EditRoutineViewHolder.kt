package com.example.workoutnotebook.ui.editroutine.adapter.viewholder

import android.view.View
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.ui.editroutine.adapter.EditRoutineItemWrapper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_edit_routine.*

class EditRoutineViewHolder(
    override val containerView: View
) : BaseViewHolder<EditRoutineItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: EditRoutineItemWrapper) {
        model as EditRoutineItemWrapper.Routine

        edit_routine_name.setText(model.routine.routineName)
        edit_routine_sets.setText(model.routine.sets)
        edit_routine_reps.setText(model.routine.reps)
        edit_routine_weight.setText(model.routine.weight)
        edit_routine_rest.setText(model.routine.rest)
    }

}