package com.bahadir.tostbangcase.domain.usecase.products

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    operator fun invoke(): Flow<Resource<List<FiriyaUI>>>
}
