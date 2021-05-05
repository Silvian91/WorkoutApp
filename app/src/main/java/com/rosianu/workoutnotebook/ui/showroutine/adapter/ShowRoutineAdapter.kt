package com.rosianu.workoutnotebook.ui.showroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rosianu.workoutnotebook.R.layout.view_holder_routines_entry
import com.rosianu.workoutnotebook.R.layout.view_holder_routines_title
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper.ItemType.ENTRY
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper.ItemType.TITLE
import com.rosianu.workoutnotebook.ui.showroutine.adapter.viewholder.ShowRoutineEntryViewHolder
import com.rosianu.workoutnotebook.ui.showroutine.adapter.viewholder.ShowRoutineTitleViewHolder

class ShowRoutineAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ShowRoutineItemWrapper> = ArrayList()
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
            view_holder_routines_title -> ShowRoutineTitleViewHolder(view)
            else -> ShowRoutineEntryViewHolder(view)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].isFirstItem = position == 0
        (holder as BaseViewHolder<ShowRoutineItemWrapper>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            TITLE -> view_holder_routines_title
            ENTRY -> view_holder_routines_entry
        }
    }

}