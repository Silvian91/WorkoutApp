package com.example.workoutapp.showroutine.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.model.routine.RoutineEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_routine_list.view.*


class ShowRoutineViewHolder constructor(
    override val containerView: View
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

    val routineName = containerView.show_routine_name
    val routineSets = containerView.show_routine_sets
    val routineReps = containerView.show_routine_reps
    val routineWeight = containerView.show_routine_weight
    val routineRest = containerView.show_routine_rest

    fun bind(routines: RoutineEntity){
        routineName.setText(routines.routineName)
        routineSets.setText(routines.sets)
        routineReps.setText(routines.reps)
        routineWeight.setText(routines.weight)
        routineRest.setText(routines.rest)
    }
}