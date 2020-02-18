package com.example.workoutapp.showroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.showroutine.adapter.ShowRoutineItemWrapper.ItemType.TITLE
import com.example.workoutapp.showroutine.adapter.viewholder.ShowRoutineEntryViewHolder
import com.example.workoutapp.showroutine.adapter.viewholder.ShowRoutineTitleViewHolder

class ShowRoutineRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ShowRoutineItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE.ordinal -> {
                ShowRoutineTitleViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_routines_title,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ShowRoutineEntryViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.view_holder_routines_entry,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShowRoutineEntryViewHolder -> {
                val entry = items[position] as ShowRoutineItemWrapper.Entry
                entry.isFirstItem = position == 0
                holder.bind(entry)
            }
            is ShowRoutineTitleViewHolder -> {
                val title = items[position] as ShowRoutineItemWrapper.Title
                title.isFirstItem = position == 0
                holder.bind(title)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    fun setData(items: List<ShowRoutineItemWrapper>) {
        this.items = items
        notifyDataSetChanged()
    }


}