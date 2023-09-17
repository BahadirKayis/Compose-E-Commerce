package com.bahadir.tostbangcase.domain.usecase.remove

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import javax.inject.Inject

class RemoveProductUseCaseImpl @Inject constructor(private val repository: FiriyaRepository) :
    RemoveProductUseCase {
    override suspend fun removeProduct(product: FiriyaUI) = repository.deleteProduct(product)
    override suspend fun removeAllProduct(product: List<FiriyaUI>) =
        repository.deleteAllProduct(product)
}
