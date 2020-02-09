package com.example.workoutapp.showroutine.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.model.routine.RoutineEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_add_routine.view.*
import kotlinx.android.synthetic.main.layout_routine_list.view.*


class ShowRoutineViewHolder constructor(
    override val containerView: View
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val routineName = containerView.show_routine_name
    private val routineSets = containerView.show_routine_sets
    private val routineReps = containerView.show_routine_reps
    private val routineWeight = containerView.show_routine_weight
    private val routineWeightMeasurement = containerView.show_weight_measurement
    private val routineRest = containerView.show_routine_rest

    fun bind(routines: RoutineEntity){
        routineName.text = routines.routineName
        routineSets.text = routines.sets
        routineReps.text = routines.reps
        routineWeight.text = routines.weight
        routineWeightMeasurement.text = routines.weightMeasurement
        routineRest.text = routines.rest
    }
}