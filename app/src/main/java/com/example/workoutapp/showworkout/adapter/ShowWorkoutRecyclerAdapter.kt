package com.example.workoutapp.showworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.model.workout.WorkoutEntity

class ShowWorkoutRecyclerAdapter(val listener: WorkoutViewHolderListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var workouts: List<WorkoutEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowWorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_workout_list, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowWorkoutViewHolder -> {
                holder.bind(workouts.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    fun setData(workoutsList: List<WorkoutEntity>){
        workouts = workoutsList
        notifyDataSetChanged()
    }

    interface WorkoutViewHolderListener {
        fun onWorkoutClicked(workoutId: Long)
    }

}