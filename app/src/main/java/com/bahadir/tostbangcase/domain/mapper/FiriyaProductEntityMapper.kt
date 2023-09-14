package com.bahadir.tostbangcase.domain.mapper

import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import javax.inject.Inject

class FiriyaProductEntityMapper @Inject constructor() : FiriyaListMapper<FiriyaItem, FiriyaUI> {

    override fun map(input: List<FiriyaItem>): List<FiriyaUI> {
        return input.map {
            FiriyaUI(
                id = it.id,
                price = formatPrice(it.price),
                image = it.image,
                description = it.description,
                category = it.category,
                title = it.title,
            )
        }
    }

    private fun formatPrice(doubleValue: Double): String {
        val formattedValue = if (doubleValue == doubleValue.toInt().toDouble()) {
            // Noktadan sonraki kısmı 0 ise tam sayı olarak kabul edin.
            String.format("%.0f", doubleValue)
        } else {
            // Noktadan sonraki kısmı 0'dan farklı ise normal olarak formatlayın.
            String.format("%.2f", doubleValue)
        }
        return formattedValue
    }
}
