package com.example.workoutapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.workoutapp.R
import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class HomeFragment : Fragment(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        WorkoutApplication.get().components.createMainComponent().inject(this)

        presenter.setView(this)
        presenter.start()

        setOnClickListeners()
        setToolbar()
    }

    private fun setOnClickListeners() {
        add_workout.setOnClickListener { openAddWorkoutActivity() }
        show_workout.setOnClickListener { openShowWorkoutActivity() }
    }

    private fun openAddWorkoutActivity() {
        startActivity(AddWorkoutActivity.newIntent(requireContext()))
    }

    private fun openShowWorkoutActivity() {
        startActivity(ShowWorkoutActivity.newIntent(requireContext()))
    }

    private fun setToolbar() {
        (activity as AppCompatActivity)
            .setSupportActionBar(toolbar)
        toolbar.title = "Workout App"
    }

    override fun onDestroyView() {
        add_workout.setOnClickListener(null)
        show_workout.setOnClickListener(null)

        super.onDestroyView()
    }

    override fun displayChuckNorrisQuote(quotes: ChuckNorrisQuoteModel) {
        chuck_norris_quote_api.text = quotes.value
    }

}

// THE QUESTION MARK MEANS THAT THE TYPE THE QUESTION MARK IS ASSOCIATED WITH CAN BE NULL
// THIS IS A PROBLEM BECAUSE IT CAN LEAD INTO A NULLPOINTEREXCEPTION
// KOTLIN FIXED IT WITH OPTIONALS (IF YOU KNOW THAT THE TYPE CAN NEVER BE NULL, THAN IT'S NOT OPTIONAL)
// IF YOU KNOW IT COULD BE NULL YOU ADD THE QUESTION MARK AND HANDLE THE NULL CASE
// YOU CAN ADD DOUBLE BANGS THAT CONVERTS TO NON OPTIONAL - IT WILL CRASH IF IT'S NULL THO

// HOW TO HANDLE NULLABILITY IN KOTLIN (DOUBLE BANGS IS ONE OF THEM, FIND 2 MORE)