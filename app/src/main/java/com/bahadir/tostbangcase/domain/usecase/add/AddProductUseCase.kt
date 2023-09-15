package com.bahadir.tostbangcase.domain.usecase.add

import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

interface AddProductUseCase {
    suspend operator fun invoke(firiyaUI: FiriyaUI)
}
