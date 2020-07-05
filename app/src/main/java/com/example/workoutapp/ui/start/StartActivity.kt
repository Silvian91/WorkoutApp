package com.example.workoutapp.ui.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.R
import com.example.workoutapp.R.layout.activity_start
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.common.BaseActivity
import com.example.workoutapp.ui.common.adapter.FragmentAdapter
import com.example.workoutapp.ui.register.RegisterActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.activity_start.*
import javax.inject.Inject

class StartActivity : BaseActivity(), StartContract.View {

    @Inject
    lateinit var presenter: StartContract.Presenter

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fragmentAdapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(activity_start)
        WorkoutApplication.get().components.createStartComponent().inject(this)

        presenter.setView(this)

        loadFragment()
        setLayoutTab()
        onRegisterClicked()
    }

    private fun loadFragment() {
        viewPager = findViewById(R.id.start_view_pager)
        fragmentAdapter = FragmentAdapter(this)

        fragmentAdapter.addFragment(StartFirstFragment())
        fragmentAdapter.addFragment(StartSecondFragment())
        fragmentAdapter.addFragment(StartThirdFragment())

        viewPager.adapter = fragmentAdapter
    }

    private fun setLayoutTab() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.start_view_pager)

        TabLayoutMediator(tabLayout, viewPager) { tab, _ ->
            tab.icon = resources.getDrawable(R.drawable.tab_default_item)
            tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        }.attach()
    }

    private fun onRegisterClicked() {
        button_register
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe {
                presenter.registerClicked()
            }
    }

    override fun openRegister() {
        startActivity(RegisterActivity.newIntent(this))
    }

    companion object {
        fun newIntent(context: Context) =
            Intent(context, StartActivity::class.java)
    }

}