package com.bahadir.tostbangcase.domain.usecase.user.get

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(private val firiyaRepository: FiriyaRepository) :
    GetUserUseCase {
    override suspend fun invoke() = firiyaRepository.getUser()
}
