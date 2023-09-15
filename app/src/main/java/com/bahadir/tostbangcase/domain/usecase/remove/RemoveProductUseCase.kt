package com.bahadir.tostbangcase.domain.usecase.remove

import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

interface RemoveProductUseCase {
    suspend fun removeProduct(firiyaUI: FiriyaUI)
    suspend fun removeProduct(firiyaUI: List<FiriyaUI>)
}
