package com.example.workoutapp.ui.profile.adapter.viewholder

import android.view.View
import com.example.workoutapp.R
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper.RoutinesCount
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_profile_routines_count.*

class RoutinesCountViewHolder(
    override val containerView: View
) : BaseViewHolder<RoutinesCount>(containerView), LayoutContainer {

    override fun bind(model: RoutinesCount) {
        profile_routines.text = itemView.context.getString(
            R.string.text_profile_routines,
            model.routinesCount.toString()
        )
    }

}