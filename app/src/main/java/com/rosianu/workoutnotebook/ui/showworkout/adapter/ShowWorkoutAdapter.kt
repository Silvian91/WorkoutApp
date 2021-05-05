package com.rosianu.workoutnotebook.ui.showworkout.adapter

import ListDiffCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.rosianu.workoutnotebook.R.layout.view_holder_workouts
import com.rosianu.workoutnotebook.R.layout.view_holder_workouts_no_data
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_TITLE
import com.rosianu.workoutnotebook.ui.showworkout.adapter.viewholder.ShowWorkoutNoDataViewHolder
import com.rosianu.workoutnotebook.ui.showworkout.adapter.viewholder.ShowWorkoutViewHolder

class ShowWorkoutAdapter(
    private val workoutListener: WorkoutClickListener,
    private val deleteListener: WorkoutDeleteClickListener,
    private val parentLifecycle: Lifecycle
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<ShowWorkoutItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return when (viewType) {
            view_holder_workouts_no_data -> ShowWorkoutNoDataViewHolder(view)
            else -> ShowWorkoutViewHolder(view, parentLifecycle, workoutListener, deleteListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            WORKOUT_NO_DATA -> view_holder_workouts_no_data
            WORKOUT_TITLE -> view_holder_workouts
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<ShowWorkoutItemWrapper>).bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(newList: List<ShowWorkoutItemWrapper>) {
        val oldList = items
        val diffResult: DiffResult = DiffUtil.calculateDiff(
            ListDiffCallback(
                oldList,
                newList
            )
        )
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class WorkoutClickListener(val workoutListener: (workoutId: Long) -> Unit) {
        fun onWorkoutClicked(workoutId: Long) = workoutListener(workoutId)
    }

    class WorkoutDeleteClickListener(val deleteListener: (workoutId: Long) -> Unit) {
        fun onDeleteWorkout(workoutId: Long) = deleteListener(workoutId)
    }

}