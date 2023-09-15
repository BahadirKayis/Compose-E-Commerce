package com.bahadir.tostbangcase.domain.usecase.history

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SoldHistoryUseCaseImpl @Inject constructor(private val repository: FiriyaRepository) :
    SoldHistoryUseCase {
    override fun invoke() = flow { emit(repository.getSoldHistory()) }
}
