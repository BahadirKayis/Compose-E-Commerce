package com.bahadir.tostbangcase.domain.usecase.history

import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import kotlinx.coroutines.flow.Flow

interface SoldHistoryUseCase {
     operator fun invoke(): Flow<List<FiriyaSoldBasket>>
}
