package com.example.workoutapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R.layout.*
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper.ItemType.*
import com.example.workoutapp.ui.home.adapter.viewholder.ActionViewHolder
import com.example.workoutapp.ui.home.adapter.viewholder.QuoteViewHolder
import com.example.workoutapp.ui.home.adapter.viewholder.WeatherViewHolder

class HomeAdapter(
    private val listener: ButtonHolderViewListener,
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<HomeItemWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return when (viewType) {
            view_holder_home_weather -> WeatherViewHolder(view)
            view_holder_home_quote -> QuoteViewHolder(view)
            else -> ActionViewHolder(
                view,
                listener,
                parentLifecycle
            )
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<HomeItemWrapper>).bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            WEATHER -> view_holder_home_weather
            QUOTE -> view_holder_home_quote
            ACTIONS -> view_holder_home_actions
        }
    }

    fun setData(items: List<HomeItemWrapper>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ButtonHolderViewListener {
        fun onAddWorkoutClicked()
        fun onShowWorkoutClicked()
    }

}