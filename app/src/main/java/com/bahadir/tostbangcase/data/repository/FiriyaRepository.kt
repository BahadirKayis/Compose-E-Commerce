package com.bahadir.tostbangcase.data.repository

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem

interface FiriyaRepository {
    suspend fun getProduct(): Resource<List<FiriyaItem>>
    suspend fun insertProduct(product: List<FiriyaItem>)
    suspend fun deleteProduct()
    suspend fun getBasket(): Resource<List<FiriyaItem>>
    suspend fun getShoppingHistory(): Resource<List<FiriyaItem>>
}
