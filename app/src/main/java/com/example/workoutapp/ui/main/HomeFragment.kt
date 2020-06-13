package com.example.workoutapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.example.workoutapp.R
import com.example.workoutapp.R.string.*
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.fragment_home.*
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
        add_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe { presenter.addWorkoutClicked() }

        show_workout
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, ON_DESTROY))
            .subscribe { presenter.showWorkoutClicked() }
    }

    override fun openAddWorkoutActivity() {
        startActivity(AddWorkoutActivity.newIntent(requireContext()))
    }

    override fun openShowWorkoutActivity() {
        startActivity(ShowWorkoutActivity.newIntent(requireContext()))
    }

    private fun setToolbar() {
        (activity as AppCompatActivity)
            .setSupportActionBar(home_toolbar)
    }

    override fun displayWeather(name: String, temp: String) {
        // String interpolation: "The weather in ${weather.name} is ${weather.temp.toInt()} degrees."
        open_weather_api.text =
            getString(text_home_weather, name, temp)
        progress_circular_weather.visibility = View.GONE
    }

    override fun displayQuote(quote: String, author: String) {
        quotes_api.text =
            getString(text_home_quote, quote, author)
        progress_circular_quote.visibility = View.GONE
    }

    override fun showNetworkError() {
        Snackbar.make(
            home_fragment,
            text_network_error,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

}

// THE QUESTION MARK MEANS THAT THE TYPE THE QUESTION MARK IS ASSOCIATED WITH CAN BE NULL
// THIS IS A PROBLEM BECAUSE IT CAN LEAD INTO A NULLPOINTEREXCEPTION
// KOTLIN FIXED IT WITH OPTIONALS (IF YOU KNOW THAT THE TYPE CAN NEVER BE NULL, THAN IT'S NOT OPTIONAL)
// IF YOU KNOW IT COULD BE NULL YOU ADD THE QUESTION MARK AND HANDLE THE NULL CASE
// YOU CAN ADD DOUBLE BANGS THAT CONVERTS TO NON OPTIONAL - IT WILL CRASH IF IT'S NULL THO

// HOW TO HANDLE NULLABILITY IN KOTLIN (DOUBLE BANGS IS ONE OF THEM, FIND 2 MORE)