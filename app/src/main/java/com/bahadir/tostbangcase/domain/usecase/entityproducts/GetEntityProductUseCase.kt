package com.bahadir.tostbangcase.domain.usecase.entityproducts

import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow

interface GetEntityProductUseCase {
    fun getProduct(): Flow<List<FiriyaUI>>
}
