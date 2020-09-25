package com.example.workoutapp.ui.profile.adapter.viewholder

import android.view.View
import com.example.workoutapp.R
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper.WorkoutsCount
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_profile_workouts_count.*

class WorkoutsCountViewHolder(
    override val containerView: View
) : BaseViewHolder<WorkoutsCount>(containerView), LayoutContainer {

    override fun bind(model: WorkoutsCount) {
        profile_workouts.text = itemView.context.getString(
            R.string.text_profile_workouts,
            model.workoutsCount.toString()
        )
    }

}