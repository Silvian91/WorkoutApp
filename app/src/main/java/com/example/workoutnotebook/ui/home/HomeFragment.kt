package com.example.workoutnotebook.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.ui.BaseFragment
import com.example.workoutnotebook.R
import com.example.workoutnotebook.R.string.text_network_error
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.example.workoutnotebook.domain.location.model.LocationModel
import com.example.workoutnotebook.domain.weather.model.WeatherModel
import com.example.workoutnotebook.ui.home.adapter.HomeAdapter
import com.example.workoutnotebook.ui.home.adapter.HomeAdapter.ButtonHolderViewListener
import com.example.workoutnotebook.ui.home.adapter.HomeItemWrapper
import com.example.workoutnotebook.ui.showworkout.ShowWorkoutActivity
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        geocoder = Geocoder(requireContext(), Locale.getDefault())

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.run {
                    val locationModel = LocationModel(
                        this.lastLocation.longitude,
                        this.lastLocation.latitude
                    )
                    city = geocoder.getFromLocation(
                        this.lastLocation.latitude,
                        this.lastLocation.longitude,
                        1
                    )[0].locality

                    viewModel.fetchWeather(locationModel)
                }
            }
        }

        initHomeRecyclerView()
        fetchLastLocation()
        viewModel.showWorkoutsButton()
        dataResponse()
        viewModel.fetchQuote()
        weatherResponse()
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
        fetchLastLocation()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun fetchLastLocation() {
        if (EasyPermissions.hasPermissions(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location == null) {
                    requestLocationData()
                } else {
                    val locationModel = LocationModel(
                        location.longitude,
                        location.latitude
                    )
                    city = geocoder.getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )[0].locality
                    viewModel.fetchWeather(locationModel)
                }
            }
        } else {
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationData() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationClient.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
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
        weather_api.text =
            "The weather in $city is: ${weather.temp.toInt()} degrees"
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