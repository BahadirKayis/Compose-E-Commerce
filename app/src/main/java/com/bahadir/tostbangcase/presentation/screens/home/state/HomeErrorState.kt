package com.bahadir.tostbangcase.presentation.screens.home.state

import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.presentation.util.ErrorState

data class HomeErrorState(
    val networkError: ErrorState = ErrorState(),
)

val homeNetworkError = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.error_message,
)
