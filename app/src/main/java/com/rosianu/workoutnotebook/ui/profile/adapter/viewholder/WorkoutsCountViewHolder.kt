package com.rosianu.workoutnotebook.ui.profile.adapter.viewholder

import android.view.View
import com.rosianu.workoutnotebook.R
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.ui.profile.adapter.ProfileItemWrapper.WorkoutsCount
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