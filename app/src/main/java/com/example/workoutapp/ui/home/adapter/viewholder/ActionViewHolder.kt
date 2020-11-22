package com.example.workoutapp.ui.home.adapter.viewholder

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutapp.ui.home.adapter.HomeAdapter
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_home_actions.*

class ActionViewHolder(
    override val containerView: View,
    private val listener: HomeAdapter.ButtonHolderViewListener,
    private val parent: Lifecycle
) : BaseViewHolder<HomeItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: HomeItemWrapper) {
        model as HomeItemWrapper.Action

        action_holder
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(parent, ON_DESTROY))
            .subscribe {
                listener.onShowWorkoutClicked()
            }
        image_show_workout.setImageResource(model.actionIconId)
        text_show_workout.text = containerView.context.getString(model.actionTextId)
    }

}