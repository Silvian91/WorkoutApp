package com.rosianu.workoutnotebook.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.core.recyclerView.DefaultDiffUtil
import com.rosianu.workoutnotebook.R.layout.*
import com.rosianu.workoutnotebook.ui.profile.adapter.ProfileItemWrapper.ItemType.*
import com.rosianu.workoutnotebook.ui.profile.adapter.viewholder.HeaderViewHolder
import com.rosianu.workoutnotebook.ui.profile.adapter.viewholder.ProfileSectionViewHolder
import com.rosianu.workoutnotebook.ui.profile.adapter.viewholder.RoutinesCountViewHolder
import com.rosianu.workoutnotebook.ui.profile.adapter.viewholder.WorkoutsCountViewHolder

class ProfileAdapter(
    private val profileListener: ProfileListener,
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ProfileItemWrapper> = ArrayList()
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
            view_holder_profile_user_section -> ProfileSectionViewHolder(
                view,
                parentLifecycle,
                profileListener
            )
            view_holder_profile_headline -> HeaderViewHolder(view)
            view_holder_profile_workouts_count -> WorkoutsCountViewHolder(view)
            else -> RoutinesCountViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            PROFILE -> view_holder_profile_user_section
            HEADING -> view_holder_profile_headline
            WORKOUTS_COUNT -> view_holder_profile_workouts_count
            ROUTINES_COUNT -> view_holder_profile_routines_count
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<ProfileItemWrapper>).bind(items[position])
    }

    fun setData(newList: List<ProfileItemWrapper>) {
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

    class ProfileListener(val profileListener: () -> Unit) {
        fun onAddPictureClicked() = profileListener()
    }

}