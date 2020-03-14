package com.example.workoutapp.ui.showworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.ui.showworkout.adapter.viewholder.ShowWorkoutViewHolder

class ShowWorkoutRecyclerAdapter(private val listener: WorkoutViewHolderListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workouts: List<ShowWorkoutItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowWorkoutViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_workout_list,
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowWorkoutViewHolder -> {
                val workouts = workouts[position] as ShowWorkoutItemWrapper.WorkoutTitle
                holder.bind(workouts)
            }
        }
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    fun setData(workoutsList: List<ShowWorkoutItemWrapper>) {
        workouts = workoutsList
        notifyDataSetChanged()
    }

    interface WorkoutViewHolderListener {
        fun onWorkoutClicked(workoutId: Long)
    }

}