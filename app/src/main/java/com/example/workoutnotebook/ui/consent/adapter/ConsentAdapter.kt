package com.example.workoutnotebook.ui.consent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.R.layout.*
import com.example.workoutnotebook.ui.consent.adapter.ConsentItemWrapper.ItemType.*
import com.example.workoutnotebook.ui.consent.adapter.viewholder.ActionsViewHolder
import com.example.workoutnotebook.ui.consent.adapter.viewholder.BodyViewHolder
import com.example.workoutnotebook.ui.consent.adapter.viewholder.HeaderViewHolder

class ConsentAdapter(
    private val acceptListener: ActionsViewHolderAcceptListener,
    private val denyListener: ActionsViewHolderDenyListener,
    private val parentLifecycle: Lifecycle
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<ConsentItemWrapper> = ArrayList()
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
            view_holder_consent_header -> HeaderViewHolder(view)
            view_holder_consent_body -> BodyViewHolder(view)
            else -> ActionsViewHolder(view, acceptListener, denyListener, parentLifecycle)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            HEADER -> view_holder_consent_header
            BODY -> view_holder_consent_body
            ACTIONS -> view_holder_consent_actions
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder<ConsentItemWrapper>).bind(items[position])
    }

    class ActionsViewHolderAcceptListener(val acceptListener: () -> Unit) {
        fun onAcceptClicked() = acceptListener()
    }

    class ActionsViewHolderDenyListener(val denyListener: () -> Unit) {
        fun onDenyClicked() = denyListener()
    }

}