package com.rosianu.workoutnotebook.ui.showroutine.adapter.viewholder

import android.view.View
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper.Title
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_routines_entry.view.*
import kotlinx.android.synthetic.main.view_holder_routines_title.*

class ShowRoutineTitleViewHolder(
    override val containerView: View
) : BaseViewHolder<ShowRoutineItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ShowRoutineItemWrapper) {
        model as Title

        if (model.isFirstItem) {
            containerView.title_divider.visibility = View.GONE
        } else {
            containerView.title_divider.visibility = View.VISIBLE
        }

        show_routine_title.text = model.workoutTitle.title
    }
}