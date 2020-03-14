package com.example.workoutapp.ui.showroutine.adapter.viewholder

import android.view.View
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper.Entry
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_routines_entry.*
import kotlinx.android.synthetic.main.view_holder_routines_entry.view.*


class ShowRoutineEntryViewHolder(
    override val containerView: View
) : BaseViewHolder<Entry>(containerView), LayoutContainer {


    override fun bind(model: Entry) {
        if (model.isFirstItem) {
            containerView.title_divider.visibility = View.GONE
        } else {
            containerView.title_divider.visibility = View.VISIBLE
        }

        show_routine_name.text = model.routine.routineName
        show_routine_sets.text = model.routine.sets
        show_routine_reps.text = model.routine.reps
        show_routine_weight.text = model.routine.weight
        show_weight_measurement.text = model.routine.weightMeasurement
        show_routine_rest.text = model.routine.rest
    }

}