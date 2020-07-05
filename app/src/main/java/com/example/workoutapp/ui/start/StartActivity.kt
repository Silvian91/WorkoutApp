package com.example.workoutapp.ui.onboarding

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.R
import com.example.workoutapp.R.id.onboarding_view_pager
import com.example.workoutapp.R.layout.activity_onboarding
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.common.adapter.FragmentAdapter
import com.example.workoutapp.ui.home.HomeFragment
import com.example.workoutapp.ui.profile.ProfileFragment

class OnboardingActivity : BaseActivity(){

    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_onboarding)

        loadFragment()
    }

    private fun loadFragment() {
        viewPager = findViewById(onboarding_view_pager)
        fragmentAdapter = FragmentAdapter(this)
        fragmentAdapter.addFragment(OnboardingFragment1())
        fragmentAdapter.addFragment(OnboardingFragment2())
        fragmentAdapter.addFragment(OnboardingFragment3())
        viewPager.adapter = fragmentAdapter
    }

}