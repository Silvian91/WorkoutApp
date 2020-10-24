package com.example.workoutapp.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.R
import com.example.workoutapp.R.layout.activity_onboarding
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.common.adapter.FragmentAdapter
import com.example.workoutapp.ui.consent.ConsentActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : BaseActivity() {

    private lateinit var viewModel: OnboardingViewModel

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_onboarding)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(OnboardingViewModel::class.java)

        loadFragment()
        setLayoutTab()
        onRegisterClicked()
        registerResponse()
    }

    private fun loadFragment() {
        viewPager = findViewById(R.id.view_pager)
        fragmentAdapter = FragmentAdapter(this)

        fragmentAdapter.addFragment(
            OnboardingFragment.newInstance(
                R.string.text_start_screen1, R.drawable.ic_undraw_personal_training_0dqn
            )
        )
        fragmentAdapter.addFragment(
            OnboardingFragment.newInstance(
                R.string.text_start_screen2, R.drawable.ic_undraw_notebook_tlkl
            )
        )
        fragmentAdapter.addFragment(
            OnboardingFragment.newInstance(
                R.string.text_start_screen3, R.drawable.ic_undraw_fitness_stats_sht6
            )
        )

        viewPager.adapter = fragmentAdapter
    }

    private fun setLayoutTab() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
            tab.icon = resources.getDrawable(R.drawable.tab_default_item, null)
            tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        }.attach()
    }

    private fun onRegisterClicked() {
        button_register
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                viewModel.registerClicked()
            }
    }

    private fun registerResponse() {
        viewModel.register
            .doOnIoObserveOnMain()
            .subscribeBy {
                showConsent()
            }
            .addTo(compositeDisposable)
    }

    private fun showConsent() = startActivity(
        ConsentActivity.newIntent(this)
    )


    companion object {
        fun newIntent(context: Context) =
            Intent(context, OnboardingActivity::class.java)
    }

}