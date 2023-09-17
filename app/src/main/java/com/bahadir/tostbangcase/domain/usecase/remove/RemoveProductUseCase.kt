package com.bahadir.tostbangcase.domain.usecase.remove

import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

interface RemoveProductUseCase {
    suspend fun removeProduct(product: FiriyaUI)
    suspend fun removeAllProduct(product: List<FiriyaUI>)
}
