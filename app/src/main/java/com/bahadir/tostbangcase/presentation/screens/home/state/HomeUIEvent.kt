package com.bahadir.tostbangcase.presentation.screens.home.state

import com.bahadir.tostbangcase.delegation.ees.Event

sealed class HomeUIEvent : Event {
    object NavigateDetail : HomeUIEvent()
}
