package com.bahadir.tostbangcase.domain.usecase.user.add

import com.bahadir.tostbangcase.data.model.User

interface AddUserUseCase {
    suspend operator fun invoke(user: User)
}
