package com.example.workoutnotebook.ui.consent.adapter.viewholder

import android.view.View
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutnotebook.ui.consent.adapter.ConsentItemWrapper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_consent_body.*

class BodyViewHolder(
    override val containerView: View
) : BaseViewHolder<ConsentItemWrapper.Body>(containerView), LayoutContainer {

    override fun bind(model: ConsentItemWrapper.Body) {
        consent_body.text = containerView.context.getString(model.body)
    }

}