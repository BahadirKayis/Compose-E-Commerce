package com.bahadir.tostbangcase.presentation.screens.basket.state

import com.bahadir.tostbangcase.delegation.ees.State
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

data class BasketUIState(
    val isLoading: Boolean = false,
    val uiData: List<FiriyaUI>? = null,
    val bottomSheetOpen: Boolean = false,
    val navigateToHomeCardName: String? = null,
) : State
