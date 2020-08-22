package com.example.workoutapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_network_error
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.common.BaseFragment
import com.example.workoutapp.ui.home.adapter.HomeAdapter
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    private lateinit var homeAdapter: HomeAdapter

    override fun showData(items: List<HomeItemWrapper>) {
        homeAdapter.items = items
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter.setView(this)
        initHomeRecyclerView()
        presenter.start()
    }

    private fun initHomeRecyclerView() {
        recycler_view_home_fragment.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            homeAdapter = HomeAdapter(presenter, this@HomeFragment.lifecycle)
            adapter = homeAdapter
        }
    }

    override fun showQoute(quote: QuoteModel) {
        quote_api.text = "\"${quote.quote}\" - ${quote.author}"
    }

    override fun showWeather(weather: WeatherModel) {
        weather_api.text = "The weather in Berlin is: ${weather.temp.toInt()} ${weather.name}"
    }

    override fun handleShowWorkoutClick() {
        startActivity(ShowWorkoutActivity.newIntent(requireContext()))
    }

    override fun showNetworkError() {
        Snackbar.make(
            home_fragment,
            text_network_error,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDestroyView() {
        presenter.finish()

        super.onDestroyView()
    }
}

// THE QUESTION MARK MEANS THAT THE TYPE THE QUESTION MARK IS ASSOCIATED WITH CAN BE NULL
// THIS IS A PROBLEM BECAUSE IT CAN LEAD INTO A NULLPOINTEREXCEPTION
// KOTLIN FIXED IT WITH OPTIONALS (IF YOU KNOW THAT THE TYPE CAN NEVER BE NULL, THAN IT'S NOT OPTIONAL)
// IF YOU KNOW IT COULD BE NULL YOU ADD THE QUESTION MARK AND HANDLE THE NULL CASE
// YOU CAN ADD DOUBLE BANGS THAT CONVERTS TO NON OPTIONAL - IT WILL CRASH IF IT'S NULL THO

// HOW TO HANDLE NULLABILITY IN KOTLIN (DOUBLE BANGS IS ONE OF THEM, FIND 2 MORE)