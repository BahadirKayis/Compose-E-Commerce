package com.bahadir.tostbangcase.presentation.screens.login.state

import com.bahadir.tostbangcase.R

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.error_message,
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.error_message,
)
