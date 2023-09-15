package com.bahadir.tostbangcase.domain.usecase.sold

import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

interface SoldBasketProductUseCase {
    suspend fun invoke(sold: List<FiriyaUI>)
}
