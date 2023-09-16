package com.bahadir.tostbangcase.presentation.screens.login.state

import androidx.annotation.StringRes
import com.bahadir.tostbangcase.delegation.ees.State

data class LoginState(
    val emailOrMobile: String = "",
    val password: String = "",
    val name: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isSuccessful: Boolean = false,
    val screenName: ScreenName = ScreenName.LOGIN,
) : State

data class LoginErrorState(
    val emailOrMobileErrorState: ErrorState = ErrorState(),
    val passwordErrorState: ErrorState = ErrorState(),
    val nameErrorState: ErrorState = ErrorState(),
)

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int? = null,
)

enum class ScreenName(val screenName: String) {
    LOGIN("Login"),
    REGISTER("Register"),
    NONE("None"),
}
