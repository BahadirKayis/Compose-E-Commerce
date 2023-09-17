package com.bahadir.tostbangcase.presentation.screens.detail.state

import com.bahadir.tostbangcase.delegation.ees.State
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

data class DetailUIState(
    val isLoading: Boolean = false,
    val uiData: FiriyaUI? = null,

) : State
