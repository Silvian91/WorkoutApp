package com.example.workoutapp.di.consent

import androidx.lifecycle.ViewModel
import com.example.workoutapp.di.ViewModelKey
import com.example.workoutapp.ui.consent.ConsentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ConsentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConsentViewModel::class)
    fun bindsConsentViewModel(viewModel: ConsentViewModel): ViewModel

}