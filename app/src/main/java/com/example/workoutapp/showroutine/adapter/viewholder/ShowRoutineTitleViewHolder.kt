package com.example.workoutapp.showroutine.adapter.viewholder

import android.view.View
import com.example.workoutapp.common.BaseViewHolder
import com.example.workoutapp.showroutine.adapter.ShowRoutineItemWrapper.Title
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_routines_entry.view.*
import kotlinx.android.synthetic.main.view_holder_routines_title.*

class ShowRoutineTitleViewHolder(
    override val containerView: View
) : BaseViewHolder<Title>(containerView), LayoutContainer {

    override fun bind(model: Title) {
        if (model.isFirstItem) {
            containerView.title_divider.visibility = View.GONE
        } else {
            containerView.title_divider.visibility = View.VISIBLE
        }

        show_routine_title.text = model.title
    }
}