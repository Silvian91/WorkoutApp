package com.example.workoutapp.ui.showworkout.adapter.viewholder

import android.view.View
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import kotlinx.android.extensions.LayoutContainer

class ShowWorkoutNoDataViewHolder(
    override val containerView: View
) : BaseViewHolder<ShowWorkoutItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ShowWorkoutItemWrapper) {}

}