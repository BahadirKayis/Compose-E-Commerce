package com.bahadir.tostbangcase.domain.usecase.add

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(private val repository: FiriyaRepository) :
    AddProductUseCase {
    override suspend fun invoke(firiyaUI: FiriyaUI) = repository.addProduct(firiyaUI)
}
