package com.example.workoutnotebook.ui.showworkout.adapter.viewholder

import android.view.View
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import kotlinx.android.extensions.LayoutContainer

class ShowWorkoutNoDataViewHolder(
    override val containerView: View
) : BaseViewHolder<ShowWorkoutItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ShowWorkoutItemWrapper) {}

}