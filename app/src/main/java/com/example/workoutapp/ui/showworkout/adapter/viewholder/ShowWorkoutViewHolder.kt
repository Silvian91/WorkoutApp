package com.example.workoutapp.ui.showworkout.adapter.viewholder

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Lifecycle
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutAdapter
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import com.google.android.material.behavior.SwipeDismissBehavior
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_workouts.*

class ShowWorkoutViewHolder(
    override val containerView: View,
    private val parent: Lifecycle,
    private val workoutListener: ShowWorkoutAdapter.WorkoutClickListener,
    private val deleteListener: ShowWorkoutAdapter.WorkoutDeleteClickListener
) : BaseViewHolder<ShowWorkoutItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ShowWorkoutItemWrapper) {
        model as WorkoutTitle

        button_show_workout_name.text = model.workoutTitle.title
        show_workout_name_card
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(parent, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                workoutListener.onWorkoutClicked(model.workoutTitle.id!!)
            }
        val behavior = SwipeDismissBehavior<View>()
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END)
        val coordinatorParameters =
            show_workout_name_card.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorParameters.behavior = behavior
        behavior.listener = object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {
                deleteListener.onDeleteWorkout(model.workoutTitle.id!!)
            }

            override fun onDragStateChanged(state: Int) {
                if (state == SwipeDismissBehavior.STATE_SETTLING) {
                    show_workout_name_card.isDragged = true
                } else if (state == SwipeDismissBehavior.STATE_IDLE) {
                    show_workout_name_card.isDragged = false
                }
            }
        }
    }

    fun resetCard() {
        (show_workout_name_card
            .layoutParams as CoordinatorLayout.LayoutParams).setMargins(0, 0, 0, 0)
        show_workout_name_card.alpha = 1.0f
        show_workout_name_card.requestLayout()
    }

}