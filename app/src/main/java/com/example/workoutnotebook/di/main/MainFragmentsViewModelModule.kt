package com.example.workoutnotebook.di.main

import androidx.lifecycle.ViewModel
import com.example.workoutnotebook.di.FragmentScope
import com.example.workoutnotebook.di.ViewModelKey
import com.example.workoutnotebook.ui.home.HomeFragment
import com.example.workoutnotebook.ui.main.MainViewModel
import com.example.workoutnotebook.ui.profile.ProfileFragment
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