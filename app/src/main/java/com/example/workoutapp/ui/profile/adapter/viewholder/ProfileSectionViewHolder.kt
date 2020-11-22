package com.example.workoutapp.ui.profile.adapter.viewholder

import android.view.View
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.R
import com.example.core.recyclerView.BaseViewHolder
import com.example.workoutapp.ui.profile.adapter.ProfileAdapter
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_profile_user_section.*

class ProfileSectionViewHolder(
    override val containerView: View,
    private val parent: Lifecycle,
    private val profileListener: ProfileAdapter.ProfileListener
) : BaseViewHolder<ProfileItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: ProfileItemWrapper) {
        model as ProfileItemWrapper.Profile

        if (model.profilePicture == null) {
            button_add_profile_picture.setImageResource(R.drawable.ic_profile_image)
        } else {
            button_add_profile_picture.setImageBitmap(model.profilePicture)
        }

        profile_username.text = model.username

        button_add_profile_picture
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(parent, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                profileListener.onAddPictureClicked()
            }
    }

}