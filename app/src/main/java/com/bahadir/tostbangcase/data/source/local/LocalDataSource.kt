package com.bahadir.tostbangcase.data.source.local

import com.bahadir.tostbangcase.data.model.FiriyaItem

interface LocalDataSource {
    suspend fun getProduct(): List<FiriyaItem>
    suspend fun insertProduct(product: List<FiriyaItem>)
    suspend fun deleteProduct()
}
