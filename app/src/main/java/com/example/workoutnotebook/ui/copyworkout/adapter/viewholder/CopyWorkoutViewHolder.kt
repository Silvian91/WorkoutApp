package com.example.workoutnotebook.ui.copyworkout.adapter.viewholder

import android.view.View
import androidx.lifecycle.Lifecycle
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_copy_workout.*

class CopyWorkoutViewHolder(
    override val containerView: View,
    private val parent: Lifecycle,
) : BaseViewHolder<ShowWorkoutItemWrapper>(containerView), LayoutContainer  {

    override fun bind(model: ShowWorkoutItemWrapper) {
        model as WorkoutTitle

        radio_workout.text = model.workoutTitle.title
    }

}