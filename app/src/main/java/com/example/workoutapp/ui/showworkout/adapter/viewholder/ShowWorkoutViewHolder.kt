package com.example.workoutapp.ui.showworkout.adapter.viewholder

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter
import com.google.android.material.behavior.SwipeDismissBehavior
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_workouts.*

class ShowWorkoutViewHolder(
    override val containerView: View,
    private val listener: ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener
) : BaseViewHolder<ShowWorkoutItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ShowWorkoutItemWrapper) {
        model as WorkoutTitle
        button_show_workout_name.text = model.workoutTitle.title
        show_workout_name_card.setOnClickListener {
            listener.onWorkoutClicked(model.workoutTitle.id!!)
        }
        val behavior = SwipeDismissBehavior<View>()
        behavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END)
        val coordinatorParameters =
            show_workout_name_card.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorParameters.behavior = behavior
        behavior.listener = object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {

                // THIS SHOULD CALL THE PRESENTER:
                // THE PRESENTER SHOULD DELETE THE WORKOUT (AND KEEP A COPY FOR UNDO)
                // SHOULD ALSO CALL THE VIEW TO SHOW THE SNACKBAR
                // IN THE SNACKBAR, IF UNDO WAS CLICKED, THE PRESENTER SHOULD BE CALLED AND A COPY
                // OF THE DELETED ITEM SHOULD BE WRITTEN TO THE DATABASE
                // ALSO EVERYTIME SOMETHING CHANGES LIKE DELETE&UNDO THAN THE PRESENTER
                // SHOULD SET THE DATA OF THE ADAPTER NEW (CALL THE SETDATA FUNCTION AGAIN)
                // USE DIFFUTIL WITH RECYCLERVIEW SO IT ONLY REDRAWS THE ITEM CHANGED
                val workoutsList = ArrayList<ShowWorkoutItemWrapper>()
                listener.onSwipeToDelete(model.workoutTitle.id!!, workoutsList)
            }

            override fun onDragStateChanged(state: Int) {
                if (state == SwipeDismissBehavior.STATE_SETTLING) {
                    show_workout_name_card.isDragged = true
                } else if (state == SwipeDismissBehavior.STATE_IDLE) {
                    show_workout_name_card.isDragged = false
                }
            }
        }

//        draggable_workout_layout.setViewDragListener(
//            object : DraggableCoordinatorLayout.ViewDragListener {
//
//                override fun onViewCaptured(view: View, i: Int) {
//                    Timber.d("//VIEW DRAG LISTENER TESTING onViewCaptured")
//                }
//
//                override fun onViewReleased(view: View, v: Float, v1: Float) {
//                    Timber.d("//VIEW DRAG LISTENER TESTING onViewReleased")
//                }
//
//            }
//        )


    }

    fun resetCard() {
        (show_workout_name_card
            .layoutParams as CoordinatorLayout.LayoutParams).setMargins(0, 0, 0, 0)
        show_workout_name_card.alpha = 1.0f
        show_workout_name_card.requestLayout()
    }


//    show_workout_name_card.setCallback???
//        object : ViewDragHelper.Callback() {
//            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
//                super.onViewReleased(releasedChild, xvel, yvel)
//            }
//
//            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        }

}