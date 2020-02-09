package com.example.workoutapp.showworkout.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.model.workout.WorkoutEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_workout_list.view.*

class ShowWorkoutViewHolder(
    override val containerView: View, private val listener: ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener
    ): RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val workoutName = containerView.button_show_workout_name
        private val workoutButton = containerView.show_workout_name


    fun bind(workouts: WorkoutEntity){
        workoutName.text = workouts.title
        workoutButton.setOnClickListener {
            listener.onWorkoutClicked(workouts.id)
        }
    }

}