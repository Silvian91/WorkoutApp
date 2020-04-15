package com.example.workoutapp.ui.showworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R.layout.view_holder_workouts
import com.example.workoutapp.R.layout.view_holder_workouts_no_data
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.example.workoutapp.ui.showworkout.adapter.viewholder.ShowWorkoutNoDataViewHolder
import com.example.workoutapp.ui.showworkout.adapter.viewholder.ShowWorkoutViewHolder

class ShowWorkoutRecyclerAdapter(private val listener: WorkoutViewHolderListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<ShowWorkoutItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return if (viewType == view_holder_workouts_no_data) {
            ShowWorkoutNoDataViewHolder(view)
        } else {
            ShowWorkoutViewHolder(view, listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            WORKOUT_NO_DATA -> view_holder_workouts_no_data
            else -> view_holder_workouts
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as BaseViewHolder<ShowWorkoutItemWrapper>
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(workoutsList: List<ShowWorkoutItemWrapper>) {
        items = workoutsList
        notifyDataSetChanged()
    }

    interface WorkoutViewHolderListener {
        fun onWorkoutClicked(workoutId: Long)
    }

}