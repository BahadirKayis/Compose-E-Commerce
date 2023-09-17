package com.bahadir.tostbangcase.data.repository

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow

interface FiriyaRepository {
    suspend fun getProduct(): Resource<List<FiriyaItem>>
    suspend fun addProduct(product: FiriyaUI)
    suspend fun deleteProduct(product: FiriyaUI)
    suspend fun deleteAllProduct(product: List<FiriyaUI>)
    suspend fun soldBasket(basket: FiriyaSoldBasket)
    suspend fun getSoldHistory(): List<FiriyaSoldBasket>
    fun getEntityProduct(): Flow<List<FiriyaUI>>
    suspend fun addUser(user: User)
    suspend fun getUser(): User?
}
