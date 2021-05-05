package com.rosianu.workoutnotebook.ui.profile.adapter.viewholder

import android.view.View
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.ui.profile.adapter.ProfileItemWrapper.Heading
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_profile_headline.*

class HeaderViewHolder(
    override val containerView: View
) : BaseViewHolder<Heading>(containerView), LayoutContainer{

    override fun bind(model: Heading) {
        profile_header.text = containerView.context.getString(model.header)
    }

}