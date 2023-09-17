package com.bahadir.tostbangcase.presentation.screens.login.state

import com.bahadir.tostbangcase.delegation.ees.State

data class LoginState(
    val emailOrMobile: String = "",
    val password: String = "",
    val name: String = "",
    val errorState: LoginErrorState = LoginErrorState(),
    val isSuccessful: Boolean = false,
    val screenType: ScreenType = ScreenType.LOGIN,
) : State
