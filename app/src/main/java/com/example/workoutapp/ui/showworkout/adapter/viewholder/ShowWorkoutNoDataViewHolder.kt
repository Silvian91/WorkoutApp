package com.example.workoutapp.ui.showworkout.adapter.viewholder

import android.view.View
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_workouts_no_data.*

class ShowWorkoutNoDataViewHolder(
    override val containerView: View
) : BaseViewHolder<WorkoutNoData>(containerView), LayoutContainer {

    override fun bind(model: WorkoutNoData) {
        text_no_data.text = model.noWorkouts.noDataTest
    }


}