package com.example.workoutapp.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> internal constructor(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bind(model: T)

}