package com.rosianu.workoutnotebook.ui.consent.adapter.viewholder

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.rosianu.core.recyclerView.BaseViewHolder
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentAdapter
import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentItemWrapper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_holder_consent_body.*


class BodyViewHolder(
    override val containerView: View,
    private val linkListener: ConsentAdapter.PdfLink
) : BaseViewHolder<ConsentItemWrapper.Body>(containerView), LayoutContainer {

    override fun bind(model: ConsentItemWrapper.Body) {
        consent_body.text = containerView.context.getString(model.body)
        setUpSignUpAction()
    }

    private fun setUpSignUpAction() {
        val signUpString = containerView.resources.getString(R.string.text_consent_link)
        val spannableString = SpannableString(signUpString)

        val setColor = ForegroundColorSpan(containerView.resources.getColor(R.color.colorPrimary, null))

        val clickAction = object : ClickableSpan() {
            override fun updateDrawState(drawState: TextPaint) {
                drawState.isUnderlineText = false
            }

            override fun onClick(widget: View) {
                linkListener.onLinkClicked()
            }
        }

        spannableString.setSpan(clickAction, 27, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(setColor, 27, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        consent_link.text = spannableString
        consent_link.movementMethod = LinkMovementMethod.getInstance()
    }

}