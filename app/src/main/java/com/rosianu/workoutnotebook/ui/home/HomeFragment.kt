package com.rosianu.workoutnotebook.ui.home

import android.Manifest
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rosianu.core.ui.BaseFragment
import com.rosianu.workoutnotebook.R
import com.rosianu.workoutnotebook.R.string.text_network_error
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.rosianu.workoutnotebook.ui.home.adapter.HomeAdapter
import com.rosianu.workoutnotebook.ui.home.adapter.HomeAdapter.ButtonHolderViewListener
import com.rosianu.workoutnotebook.ui.home.adapter.HomeItemWrapper
import com.rosianu.workoutnotebook.ui.showworkout.ShowWorkoutActivity
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_home.*
import pub.devrel.easypermissions.EasyPermissions
import java.util.*

class HomeFragment : BaseFragment(), EasyPermissions.PermissionCallbacks {

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var geocoder: Geocoder
    private var city: String = ""

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

        geocoder = Geocoder(requireContext(), Locale.getDefault())
        initHomeRecyclerView()
        //fetchLastLocation()
        viewModel.showWorkoutsButton()
        dataResponse()
        viewModel.fetchQuote()
        //weatherResponse()
        quoteResponse()
        showWorkoutClicked()
        onError()
    }

    private fun initHomeRecyclerView() {
        recycler_view_home_fragment.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            homeAdapter =
                HomeAdapter(
                    ButtonHolderViewListener { viewModel.onShowWorkoutClicked() },
                    this@HomeFragment.lifecycle
                )
            adapter = homeAdapter
        }
    }

    companion object {
        private const val REQUEST_CODE = 1010
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(REQUEST_CODE, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //fetchLastLocation()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
    }

//    @SuppressLint("MissingPermission")
//    private fun fetchLastLocation() {
//        if (EasyPermissions.hasPermissions(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//        ) {
//            viewModel.fetchCurrentLocation()
//            getUserLocation()
//
//        } else {
//            requestPermission()
//        }
//    }

//    private fun getUserLocation(){
//        viewModel.location
//            .doOnIoObserveOnMain()
//            .subscribeBy {
//                city = geocoder.getFromLocation(
//                    viewModel.location.value!!.latitude,
//                    viewModel.location.value!!.longitude,
//                    1
//                )[0].locality
//                viewModel.fetchWeather(viewModel.location.value!!)
//            }
//            .addTo(compositeDisposable)
//    }

//    private fun weatherResponse() {
//        viewModel.weather
//            .doOnIoObserveOnMain()
//            .subscribeBy {
//                showWeather(viewModel.weather.value!!)
//                hideWeatherLoading()
//            }
//            .addTo(compositeDisposable)
//    }

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

//    private fun hideWeatherLoading() {
//        progress_circular_weather.visibility = View.GONE
//    }

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

//    private fun showWeather(weather: WeatherModel) {
//        weather_api.text =
//            "The weather in $city is: ${weather.temp.toInt()} degrees"
//    }

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