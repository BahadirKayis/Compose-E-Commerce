package com.bahadir.tostbangcase.domain.mapper

import com.bahadir.tostbangcase.core.extensions.formatPrice
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import javax.inject.Inject

class FiriyaProductEntityMapper @Inject constructor() : FiriyaListMapper<FiriyaItem, FiriyaUI> {

    override fun map(input: List<FiriyaItem>): List<FiriyaUI> {
        return input.map {
            FiriyaUI(
                id = it.id,
                price = it.price.formatPrice(),
                image = it.image,
                description = it.description,
                category = it.category,
                title = it.title,
            )
        }
    }


}
