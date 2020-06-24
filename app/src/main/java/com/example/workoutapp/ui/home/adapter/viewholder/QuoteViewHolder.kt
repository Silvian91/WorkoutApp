package com.example.workoutapp.ui.home.adapter.viewholder

import android.view.View
import com.example.workoutapp.R.string.text_home_quote
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper.Quote
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_home_quote.*

class QuoteViewHolder(
    override val containerView: View
) : BaseViewHolder<HomeItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: HomeItemWrapper) {
        model as Quote
        quote_api.text = itemView.context.getString(
            text_home_quote,
            model.quoteText.quote,
            model.quoteText.author
        )
        progress_circular_quote.visibility = View.GONE
    }

}