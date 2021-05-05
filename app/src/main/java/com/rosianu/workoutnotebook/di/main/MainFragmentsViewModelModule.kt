package com.rosianu.workoutnotebook.di.main

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.FragmentScope
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.home.HomeFragment
import com.rosianu.workoutnotebook.ui.main.MainViewModel
import com.rosianu.workoutnotebook.ui.profile.ProfileFragment
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