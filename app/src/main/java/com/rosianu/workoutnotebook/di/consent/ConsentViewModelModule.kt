package com.rosianu.workoutnotebook.di.consent

import androidx.lifecycle.ViewModel
import com.rosianu.workoutnotebook.di.ViewModelKey
import com.rosianu.workoutnotebook.ui.consent.ConsentViewModel
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