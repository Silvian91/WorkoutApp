package com.example.workoutapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R.layout.view_holder_home_actions
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.home.adapter.viewholder.ActionViewHolder

class HomeAdapter(
    private val listener: ButtonHolderViewListener,
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<HomeItemWrapper> = ArrayList()
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
        return ActionViewHolder(
            view,
            listener,
            parentLifecycle
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<HomeItemWrapper>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return view_holder_home_actions
    }

    interface ButtonHolderViewListener {
        fun onShowWorkoutClicked()
    }

}