package com.example.workoutnotebook.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.core.ui.BaseActivity
import com.example.workoutnotebook.R
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.ui.addworkout.AddWorkoutActivity
import com.example.workoutnotebook.ui.common.adapter.FragmentAdapter
import com.example.workoutnotebook.ui.home.HomeFragment
import com.example.workoutnotebook.ui.profile.ProfileFragment
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentAdapter: FragmentAdapter
    private var fabOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory.get()).get(MainViewModel::class.java)

        loadFragment()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                //TODO: Check in the menu item if the id is defined
                R.id.nav_home -> viewPager.currentItem = 0
                else -> viewPager.currentItem = 1
            }
            true
        }

        onNavigationSwipe()
        onClickListener()
        listenForFab()
        listenForAddWorkout()
    }

    private fun onNavigationSwipe() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottom_navigation.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun listenForFab() {
        fab_menu.setOnClickListener {
            if (!fabOpen) {
                fab_add_workout.animation = AnimationUtils.loadAnimation(this, R.anim.fab_open)
                fab_add_workout.visibility = View.VISIBLE
                fab_copy_workout.animation = AnimationUtils.loadAnimation(this, R.anim.fab_open)
                fab_copy_workout.visibility = View.VISIBLE
                fabOpen = true
            } else {
                fab_add_workout.animation = AnimationUtils.loadAnimation(this, R.anim.fab_close)
                fab_add_workout.visibility = View.INVISIBLE
                fab_copy_workout.animation = AnimationUtils.loadAnimation(this, R.anim.fab_close)
                fab_copy_workout.visibility = View.INVISIBLE
                fabOpen = false
            }
        }
    }

    private fun onClickListener() {
        fab_add_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.onFloatingClicked()
            }
    }

    private fun listenForAddWorkout() {
        viewModel.addWorkoutClicked
            .doOnIoObserveOnMain()
            .subscribeBy {
                openAddWorkoutActivity()
            }
            .addTo(compositeDisposable)
    }

    private fun openAddWorkoutActivity() = startActivity(
        AddWorkoutActivity.newIntent(this)
    )

    private fun loadFragment() {
        viewPager = findViewById(R.id.view_pager)
        fragmentAdapter = FragmentAdapter(this)
        //TODO: newInstance here
        fragmentAdapter.addFragment(HomeFragment())
        fragmentAdapter.addFragment(ProfileFragment())
        viewPager.adapter = fragmentAdapter
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}
