package com.rosianu.workoutnotebook.ui.copyworkout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.core.recyclerView.DefaultDiffUtil
import com.rosianu.workoutnotebook.R.layout.*
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper.ItemType.WORKOUT_TITLE
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_copy_workout.*

class CopyWorkoutAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<WorkoutItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return when (viewType) {
            view_holder_copy_workouts_no_data -> CopyWorkoutNoDataViewHolder(view)
            else -> CopyWorkoutViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            WORKOUT_NO_DATA -> view_holder_copy_workouts_no_data
            WORKOUT_TITLE -> view_holder_copy_workout
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<WorkoutItemWrapper>).bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(newList: List<WorkoutItemWrapper>) {
        val oldList = items
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            DefaultDiffUtil(
                oldList,
                newList
            )
        )
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class CopyWorkoutViewHolder(
        override val containerView: View,
    ) : BaseViewHolder<WorkoutItemWrapper>(containerView), LayoutContainer {

        override fun bind(model: WorkoutItemWrapper) {
            radio_workout.setOnCheckedChangeListener(null)
            model as WorkoutItemWrapper.WorkoutTitle

            radio_workout.text = model.workout.title
            radio_workout.isChecked = model.checked
            copy_workout_view_holder.setOnClickListener {
                model.checkChangeListener.invoke()
            }
            radio_workout.setOnCheckedChangeListener { buttonView, isChecked -> model.checkChangeListener.invoke() }
        }

    }

    class CopyWorkoutNoDataViewHolder(
        override val containerView: View
    ) : BaseViewHolder<WorkoutItemWrapper>(containerView), LayoutContainer {

        override fun bind(model: WorkoutItemWrapper) {}

    }

}
