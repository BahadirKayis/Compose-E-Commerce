package com.bahadir.tostbangcase.domain.usecase.entityproducts

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntityProductUseCaseImpl @Inject constructor(val repository: FiriyaRepository) :
    GetEntityProductUseCase {
    override fun getProduct(): Flow<List<FiriyaUI>> = repository.getEntityProduct()
}
