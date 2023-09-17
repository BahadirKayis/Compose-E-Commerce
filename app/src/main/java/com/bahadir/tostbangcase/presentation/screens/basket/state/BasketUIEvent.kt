package com.bahadir.tostbangcase.presentation.screens.basket.state

import com.bahadir.tostbangcase.delegation.ees.Event
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

sealed class BasketUIEvent : Event {
    data class UpdateProduct(val product: FiriyaUI, val state: ProductState) : BasketUIEvent()
    data class BottomSheetBasketSold(val cardInfo: String) :
        BasketUIEvent()
}
