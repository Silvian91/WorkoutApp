package com.example.workoutapp.ui.profile.adapter.viewholder

import android.view.View
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper
import kotlinx.android.extensions.LayoutContainer

class ViewHolderProfileSection(
    override val containerView: View
) : BaseViewHolder<ProfileItemWrapper>(containerView), LayoutContainer {


    override fun bind(model: ProfileItemWrapper) {

    }

}