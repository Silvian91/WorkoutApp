package com.example.workoutapp.di.main

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.FragmentScope
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.addroutine.AddRoutineViewModel
import com.example.workoutapp.ui.home.HomeFragment
import com.example.workoutapp.ui.main.MainViewModel
import com.example.workoutapp.ui.profile.ProfileFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface MainFragmentsViewModelModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel) : ViewModel


}