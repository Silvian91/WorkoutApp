package com.example.workoutapp.ui.profile.adapter.viewholder

import android.view.View
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.domain.user.model.UserModel
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.profile.adapter.ProfileAdapter
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
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
            button_add_profile_picture.setImageResource(model.defaultUserPic)
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