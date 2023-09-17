package com.bahadir.tostbangcase.presentation.screens.login.state

import com.bahadir.tostbangcase.delegation.ees.Event

sealed class LoginUiEvent : Event {
    data class EmailOrMobileChanged(val inputValue: String) : LoginUiEvent()
    data class PasswordChanged(val inputValue: String) : LoginUiEvent()
    data class NameChange(val inputValue: String) : LoginUiEvent()
    data class SendUser(val screenState: ScreenType) : LoginUiEvent()
    data class ChangeScreen(val screenState: ScreenType) : LoginUiEvent()
}
