package com.bahadir.tostbangcase.presentation.screens.detail.state

import com.bahadir.tostbangcase.delegation.ees.Event
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

sealed class DetailUIEvent : Event {
    data class AddProduct(val product: FiriyaUI) : DetailUIEvent()
}
