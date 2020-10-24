package com.example.workoutapp.ui.consent.adapter.viewholder

import android.view.View
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.consent.adapter.ConsentAdapter
import com.example.workoutapp.ui.consent.adapter.ConsentItemWrapper
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_consent_actions.*

class ActionsViewHolder(
    override val containerView: View,
    private val acceptListener: ConsentAdapter.ActionsViewHolderAcceptListener,
    private val denyListener: ConsentAdapter.ActionsViewHolderDenyListener,
    private val parent: Lifecycle
): BaseViewHolder<ConsentItemWrapper.Actions>(containerView), LayoutContainer {

    override fun bind(model: ConsentItemWrapper.Actions) {
        consent_confirm
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(parent, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                acceptListener.onAcceptClicked()
            }

        consent_deny
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(parent, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                denyListener.onDenyClicked()
            }
    }

}