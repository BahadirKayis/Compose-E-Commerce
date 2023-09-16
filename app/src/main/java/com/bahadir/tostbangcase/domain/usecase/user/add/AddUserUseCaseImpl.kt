package com.bahadir.tostbangcase.domain.usecase.user.add

import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import javax.inject.Inject

class AddUserUseCaseImpl @Inject constructor(private val repo: FiriyaRepository) : AddUserUseCase {
    override suspend fun invoke(user: User) = repo.addUser(user)
}
