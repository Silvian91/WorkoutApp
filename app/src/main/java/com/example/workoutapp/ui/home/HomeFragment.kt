package com.example.workoutapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_network_error
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.common.BaseFragment
import com.example.workoutapp.ui.home.adapter.HomeAdapter
import com.example.workoutapp.ui.home.adapter.HomeAdapter.ButtonHolderViewListener
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import com.example.workoutapp.ui.showworkout.ShowWorkoutActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(HomeViewModel::class.java)

        initHomeRecyclerView()
        viewModel.showWeatherAndQuote()
        weatherResponse()
        quoteResponse()
        dataResponse()
        showWorkoutClicked()
        onError()
    }

    private fun initHomeRecyclerView() {
        recycler_view_home_fragment.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            homeAdapter =
                HomeAdapter(ButtonHolderViewListener { viewModel.onShowWorkoutClicked() }, this@HomeFragment.lifecycle)
            adapter = homeAdapter
        }
    }

    private fun weatherResponse() {
        viewModel.weather
            .doOnIoObserveOnMain()
            .subscribeBy {
                showWeather(viewModel.weather.value!!)
                hideWeatherLoading()
            }
            .addTo(compositeDisposable)
    }

    private fun quoteResponse() {
        viewModel.quote
            .doOnIoObserveOnMain()
            .subscribeBy {
                showQuote(viewModel.quote.value!!)
                hideQuoteLoading()
            }
            .addTo(compositeDisposable)
    }

    private fun dataResponse() {
        viewModel.data
            .doOnIoObserveOnMain()
            .subscribeBy {
                showData(viewModel.data.value!!)
            }
            .addTo(compositeDisposable)
    }

    private fun hideWeatherLoading() {
        progress_circular_weather.visibility = View.GONE
    }

    private fun hideQuoteLoading() {
        progress_circular_quote.visibility = View.GONE
    }

    private fun showWorkoutClicked() {
        viewModel.showWorkout
            .doOnIoObserveOnMain()
            .subscribeBy {
                openShowWorkout()
            }
            .addTo(compositeDisposable)
    }

    private fun onError() {
        viewModel.error
            .doOnIoObserveOnMain()
            .subscribeBy {
                showNetworkError()
            }
            .addTo(compositeDisposable)
    }

    private fun showData(items: List<HomeItemWrapper>) {
        homeAdapter.items = items
    }

    private fun showQuote(quote: QuoteModel) {
        quote_api.text = "\"${quote.quote}\" - ${quote.author}"
    }

    private fun showWeather(weather: WeatherModel) {
        weather_api.text = "The weather in Berlin is: ${weather.temp.toInt()} ${weather.name}"
    }

    private fun openShowWorkout() = startActivity(ShowWorkoutActivity.newIntent(requireContext()))

    private fun showNetworkError() {
        Snackbar.make(
            home_fragment,
            text_network_error,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun onDestroyView() {
        compositeDisposable.clear()

        super.onDestroyView()
    }
}