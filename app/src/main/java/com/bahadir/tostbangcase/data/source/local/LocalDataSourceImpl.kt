package com.bahadir.tostbangcase.data.source.local

import com.bahadir.tostbangcase.data.room.FiriyaDao
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocalDataSourceImpl(private val firiya: FiriyaDao) : LocalDataSource {
    override fun getProduct(): Flow<List<FiriyaUI>> = callbackFlow {
        firiya.getProducts().collect {
            trySend(it)
        }
        awaitClose { channel.close() }
    }

    override suspend fun addProduct(product: FiriyaUI) {
        val item = firiya.findProductById(product.id)
        item?.let {
            item.count += 1
            firiya.updateProduct(item)
        } ?: kotlin.run { firiya.addProduct(product) }
    }

    override suspend fun deleteProduct(firiyaUI: FiriyaUI) {
        val item = firiya.findProductById(firiyaUI.id)
        item?.let {
            if (item.count > 1) {
                item.count -= 1
                firiya.updateProduct(item)
            } else {
                firiya.deleteProduct(item.id)
            }
        }
    }

    override suspend fun findProductById(id: Int): FiriyaUI? = firiya.findProductById(id)
    override suspend fun deleteAllProduct(firiyaUI: List<FiriyaUI>) {
        firiya.deleteAllProduct(firiyaUI)
    }

    override suspend fun addSoldHistory(product: FiriyaSoldBasket) {
        firiya.addSoldHistory(product)
    }

    override suspend fun getSoldHistory(): List<FiriyaSoldBasket> {
        return firiya.getSoldHistory()
    }
}
