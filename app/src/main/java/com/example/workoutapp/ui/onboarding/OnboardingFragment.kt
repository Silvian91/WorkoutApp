package com.example.workoutapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.R
import com.example.core.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_onboarding.*

class OnboardingFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textId = arguments?.getInt(TEXT_ID)
            ?: throw IllegalStateException("OnboardingFragment: Text id cannot be null")
        val text = getString(textId)
        onboarding_text.text = text

        val imageId = arguments?.getInt(IMAGE_ID)
            ?: throw IllegalStateException("OnboardingFragment: Image id cannot be null")
        onboarding_image.setImageResource(imageId)
    }

    companion object {
        fun newInstance(textId: Int, imageId: Int) = OnboardingFragment().apply {
            arguments = Bundle().apply {
                putInt(TEXT_ID, textId)
                putInt(IMAGE_ID, imageId)
            }
        }

        private const val TEXT_ID = "TEXT_ID"
        private const val IMAGE_ID = "IMAGE_ID"
    }

}