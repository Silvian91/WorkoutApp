package com.example.workoutapp.showworkout.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.data.workout.WorkoutEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_workout_list.view.*

class ShowWorkoutViewHolder(
    override val containerView: View, private val listener: ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val workoutName = containerView.button_show_workout_name
        private val workoutButton = containerView.show_workout_name_card


    fun bind(workouts: WorkoutEntity){
        workoutName.text = workouts.title
        workoutButton.setOnClickListener {
            listener.onWorkoutClicked(workouts.id)
        }

        //show_workout_name_card.setCallback???
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

}