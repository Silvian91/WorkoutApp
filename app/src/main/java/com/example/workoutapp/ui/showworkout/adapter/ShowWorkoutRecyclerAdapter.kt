package com.example.workoutapp.ui.showworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import com.example.workoutapp.ui.showworkout.adapter.viewholder.ShowWorkoutNoDataViewHolder
import com.example.workoutapp.ui.showworkout.adapter.viewholder.ShowWorkoutViewHolder

class ShowWorkoutRecyclerAdapter(private val listener: WorkoutViewHolderListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workouts: List<WorkoutTitle> = ArrayList()
    private var noWorkouts: List<WorkoutNoData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (workouts.isEmpty()) {
            ShowWorkoutNoDataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_workouts_no_data,
                    parent,
                    false
                )
            )
        } else {
            ShowWorkoutViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.view_holder_workouts,
                    parent,
                    false
                ),
                listener
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowWorkoutNoDataViewHolder -> {
                val noWorkouts = noWorkouts[position]
                holder.bind(noWorkouts)
            }
            is ShowWorkoutViewHolder -> {
                val workouts = workouts[position]
                holder.bind(workouts)
            }
        }
    }

    override fun getItemCount(): Int {
        return when (workouts.size) {
            0 -> noWorkouts.size
            else -> workouts.size
        }
    }

    fun setData(workoutsList: List<WorkoutTitle>) {
        workouts = workoutsList
        notifyDataSetChanged()
    }

    fun setNoData(noWorkouts: List<WorkoutNoData>) {
        this.noWorkouts = noWorkouts
        notifyDataSetChanged()
    }

    interface WorkoutViewHolderListener {
        fun onWorkoutClicked(workoutId: Long)
    }

}