package com.bahadir.tostbangcase.presentation.screens.home.state

import com.bahadir.tostbangcase.delegation.ees.State
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

data class HomeUIState(
    val isLoading: Boolean = false,
    val products: List<FiriyaUI>? = null,
    val navigateToDetail: Boolean = false,
    val errorState: HomeErrorState = HomeErrorState(),
) : State
