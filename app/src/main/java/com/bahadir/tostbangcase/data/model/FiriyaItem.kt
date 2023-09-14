package com.bahadir.tostbangcase.data.model

data class FiriyaItem(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    var price: Double,
    val rating: Rating,
    val title: String,
)
