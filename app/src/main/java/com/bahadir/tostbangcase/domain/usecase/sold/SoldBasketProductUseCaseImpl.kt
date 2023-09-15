package com.bahadir.tostbangcase.domain.usecase.sold

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class SoldBasketProductUseCaseImpl @Inject constructor(private val repository: FiriyaRepository) :
    SoldBasketProductUseCase {
    override suspend fun invoke(sold: List<FiriyaUI>) {
        val firiya = FiriyaSoldBasket(
            id = 0,
            date = date(),
            firiyaItem = sold,
        )
        repository.soldBasket(firiya)
    }

    private fun date(): String {
        val tarih = Date() // Şu anki tarihi alın
        val format = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale.getDefault(),
        )
        return format.format(tarih)
    }
}
