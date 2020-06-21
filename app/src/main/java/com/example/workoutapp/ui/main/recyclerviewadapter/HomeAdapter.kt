package com.example.workoutapp.ui.main.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R.layout.*
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper.ItemType.QUOTE
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper.ItemType.WEATHER
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper.Quote
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper.Weather
import com.example.workoutapp.ui.main.recyclerviewadapter.viewholder.ActionViewHolder
import com.example.workoutapp.ui.main.recyclerviewadapter.viewholder.QuoteViewHolder
import com.example.workoutapp.ui.main.recyclerviewadapter.viewholder.WeatherViewHolder

class HomeAdapter(
    private val listener: ButtonHolderViewListener,
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<HomeItemsWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            WEATHER.ordinal ->
                WeatherViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        view_holder_home_weather,
                        parent,
                        false
                    )
                )
            QUOTE.ordinal ->
                QuoteViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        view_holder_home_quote,
                        parent,
                        false
                    )
                )
            else -> ActionViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    view_holder_home_actions,
                    parent,
                    false
                ),
                listener,
                parentLifecycle
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> {
                val weather = items[position] as Weather
                holder.bind(weather)
            }
            is QuoteViewHolder -> {
                val quote = items[position] as Quote
                holder.bind(quote)
            }
            is ActionViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    fun setData(items: List<HomeItemsWrapper>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ButtonHolderViewListener {
        fun onAddWorkoutClicked()
        fun onShowWorkoutClicked()
    }

}