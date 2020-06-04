package com.example.workoutapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_network_error
import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.addworkout.AddWorkoutActivity
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
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
        add_workout.setOnClickListener { presenter.addWorkoutClicked() }
        show_workout.setOnClickListener { presenter.showWorkoutClicked() }
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

    override fun onDestroyView() {
        add_workout.setOnClickListener(null)
        show_workout.setOnClickListener(null)

        super.onDestroyView()
    }

    override fun displayCurrentWeather(weather: WeatherModel) {
        val weatherValue = StringBuilder()
        weatherValue.append("The weather in ")
            .append(weather.name)
            .append(" is ")
            .append(weather.temp.toInt().toString())
            .append(" degrees.")

        open_weather_api.text = weatherValue
    }

    override fun displayQuote(quote: InspirationalQuoteModel) {
        val inspirationalQuote = StringBuilder()
        inspirationalQuote.append("\"")
            .append(quote.quote)
            .append("\" - ")
            .append(quote.author)

        quotes_api.text = inspirationalQuote
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