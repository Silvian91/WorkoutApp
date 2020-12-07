package com.example.workoutnotebook.di.consent

import androidx.lifecycle.ViewModel
import com.example.workoutnotebook.di.ViewModelKey
import com.example.workoutnotebook.ui.consent.ConsentViewModel
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