package com.bahadir.tostbangcase.domain.usecase.user.get

import com.bahadir.tostbangcase.data.model.User

interface GetUserUseCase {
    suspend operator fun invoke(): User?
}
