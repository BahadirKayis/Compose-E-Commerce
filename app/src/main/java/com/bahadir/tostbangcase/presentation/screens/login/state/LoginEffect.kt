package com.bahadir.tostbangcase.presentation.screens.login.state

import androidx.compose.runtime.MutableState
import com.bahadir.animelist.presentation.base.ees.Effect

sealed class LoginEffect : Effect {
    data class ShowLoading(val isLoading: Boolean) : LoginEffect()
    data class ShowAlertDialog(
        val dialogState: MutableState<Boolean>,
        val title: String,
        val body: String,
    ) : LoginEffect()
}
