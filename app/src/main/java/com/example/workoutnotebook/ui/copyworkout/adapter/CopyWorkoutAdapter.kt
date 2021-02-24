package com.example.workoutnotebook.ui.copyworkout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.recyclerView.BaseViewHolder
import com.example.core.recyclerView.DefaultDiffUtil
import com.example.workoutnotebook.R
import com.example.workoutnotebook.ui.showworkout.adapter.viewholder.ShowWorkoutNoDataViewHolder
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
            R.layout.view_holder_workouts_no_data -> ShowWorkoutNoDataViewHolder(view)
            else -> CopyWorkoutViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            WorkoutItemWrapper.ItemType.WORKOUT_TITLE -> R.layout.view_holder_copy_workout
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

}
