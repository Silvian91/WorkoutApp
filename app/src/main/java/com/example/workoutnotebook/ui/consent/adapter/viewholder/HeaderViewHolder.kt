package com.example.workoutnotebook.ui.consent.adapter.viewholder

import android.view.View
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.ui.consent.adapter.ConsentItemWrapper.Header
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_consent_header.*

class HeaderViewHolder(
    override val containerView: View
): BaseViewHolder<Header>(containerView), LayoutContainer {

    override fun bind(model: Header) {
        consent_header.text = containerView.context.getString(model.header)
    }

}