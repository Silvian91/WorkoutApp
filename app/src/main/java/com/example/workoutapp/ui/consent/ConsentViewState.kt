package com.example.workoutapp.ui.consent

import com.example.workoutapp.ui.consent.adapter.ConsentItemWrapper

data class ConsentViewState (
    val items: MutableList<ConsentItemWrapper>,
    val accepted: Boolean,
    val dialog: Boolean,
    val denied: Boolean
)
