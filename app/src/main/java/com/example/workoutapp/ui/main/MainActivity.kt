package com.example.workoutapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.workoutapp.R
import com.example.workoutapp.ui.main.viewpageradapter.ViewPagerFragmentAdapter
import com.example.workoutapp.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentAdapter: ViewPagerFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        loadFragment()

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                //TODO: Check in the menu item if the id is defined
                R.id.nav_home -> viewPager.currentItem = 0
                else -> viewPager.currentItem = 1
            }
            true
        }
    }

    private fun loadFragment() {
        viewPager = findViewById(R.id.view_pager)
        fragmentAdapter = ViewPagerFragmentAdapter(this)
        fragmentAdapter.addFragment(HomeFragment())
        fragmentAdapter.addFragment(ProfileFragment())
        viewPager.adapter = fragmentAdapter
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

}
