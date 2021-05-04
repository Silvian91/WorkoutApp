package com.example.workoutnotebook.ui.editroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.R.layout.view_holder_edit_routine
import com.example.workoutnotebook.ui.editroutine.adapter.viewholder.EditRoutineViewHolder

class EditRoutineAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<EditRoutineItemWrapper> = ArrayList()
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
        return EditRoutineViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<EditRoutineItemWrapper>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return view_holder_edit_routine
    }

}