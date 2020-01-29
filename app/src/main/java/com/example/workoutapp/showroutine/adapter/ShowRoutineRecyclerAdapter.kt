package com.example.workoutapp.showroutine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.model.routine.RoutineEntity

class ShowRoutineRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var routinePairs: List<RoutineEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShowRoutineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_routine_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return routinePairs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShowRoutineViewHolder -> {
                holder.bind(routinePairs.get(position))
            }
        }
    }

    fun setData(routines: List<RoutineEntity>){
        routinePairs = routines
        notifyDataSetChanged()
    }


}