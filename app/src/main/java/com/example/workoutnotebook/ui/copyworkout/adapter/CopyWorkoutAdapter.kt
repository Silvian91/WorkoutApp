package com.example.workoutnotebook.ui.copyworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.R
import com.example.workoutnotebook.ui.copyworkout.adapter.viewholder.CopyWorkoutViewHolder
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutnotebook.ui.showworkout.adapter.viewholder.ShowWorkoutNoDataViewHolder

class CopyWorkoutAdapter(
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ShowWorkoutItemWrapper> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return when (viewType) {
            R.layout.view_holder_workouts_no_data -> ShowWorkoutNoDataViewHolder(view)
            else -> CopyWorkoutViewHolder(view, parentLifecycle)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            ShowWorkoutItemWrapper.ItemType.WORKOUT_NO_DATA -> R.layout.view_holder_workouts_no_data
            ShowWorkoutItemWrapper.ItemType.WORKOUT_TITLE -> R.layout.view_holder_copy_workout
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<ShowWorkoutItemWrapper>).bind(items[position])
    }

    override fun getItemCount() = items.size

}
