package com.rosianu.workoutnotebook.ui.consent

import com.rosianu.workoutnotebook.ui.consent.adapter.ConsentItemWrapper

data class ConsentViewState (
    val items: MutableList<ConsentItemWrapper>,
    val accepted: Boolean,
    val dialog: Boolean,
    val denied: Boolean
)
