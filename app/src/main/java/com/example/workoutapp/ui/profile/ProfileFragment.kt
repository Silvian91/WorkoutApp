package com.example.workoutapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.workoutapp.R
import kotlinx.android.synthetic.main.toolbar.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()

        setToolbar()
    }

    private fun setToolbar() {
        (activity as AppCompatActivity)
            .setSupportActionBar(toolbar)
        toolbar.title = "Workout App"
    }

}