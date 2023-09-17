package com.bahadir.tostbangcase.presentation.screens.login.state

import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.presentation.util.ErrorState

data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val nameErrorState: ErrorState = ErrorState(),
)

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.error_message,
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.error_message,
)

val emailNotValid = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.email_not_valid,
)
val passwordNotValid = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.password_not_valid,
)
val nameNotEmpty = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.not_empty,
)
