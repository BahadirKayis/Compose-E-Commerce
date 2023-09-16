package com.bahadir.tostbangcase.presentation.screens.shoppinghistory.state

import com.bahadir.tostbangcase.delegation.ees.State
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket

data class SoldUIState(
    val soldHistory: List<FiriyaSoldBasket>? = null,
    val isLoading: Boolean = false,
    val isLogin: Boolean = true,
) : State
