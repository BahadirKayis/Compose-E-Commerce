package com.bahadir.tostbangcase.data.source.local

import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getProduct(): Flow<List<FiriyaUI>>
    suspend fun addProduct(product: FiriyaUI)
    suspend fun deleteProduct(product: FiriyaUI)
    suspend fun findProductById(id: Int): FiriyaUI?
    suspend fun deleteAllProduct(product: List<FiriyaUI>)

    suspend fun addSoldHistory(product: FiriyaSoldBasket)

    suspend fun getSoldHistory(): List<FiriyaSoldBasket>
    suspend fun addUser(user: User)
    suspend fun getUser(): User
}
