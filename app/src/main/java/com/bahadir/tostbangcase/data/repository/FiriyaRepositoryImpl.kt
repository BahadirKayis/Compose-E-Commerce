package com.bahadir.tostbangcase.data.repository

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.data.source.local.LocalDataSource
import com.bahadir.tostbangcase.data.source.remote.RemoteDataSource
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FiriyaRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : FiriyaRepository {
    override suspend fun getProduct(): Resource<List<FiriyaItem>> =
        withContext(ioDispatcher) {
            try {
                remoteDataSource.getProduct()
            } catch (e: Exception) {
                Resource.Error(e)
            }
        }

    override suspend fun addProduct(product: FiriyaUI) {
        withContext(ioDispatcher) {
            localDataSource.addProduct(product)
        }
    }

    override suspend fun deleteProduct(product: FiriyaUI) {
        withContext(ioDispatcher) {
            localDataSource.deleteProduct(product)
        }
    }

    override suspend fun deleteAllProduct(product: List<FiriyaUI>) {
        withContext(ioDispatcher) {
            localDataSource.deleteAllProduct(product)
        }
    }

    override suspend fun soldBasket(basket: FiriyaSoldBasket) {
        withContext(ioDispatcher) {
            localDataSource.addSoldHistory(basket)
        }
    }

    override suspend fun getSoldHistory(): List<FiriyaSoldBasket> =
        withContext(ioDispatcher) {
            localDataSource.getSoldHistory()
        }

    override fun getEntityProduct(): Flow<List<FiriyaUI>> = callbackFlow {
        localDataSource.getProduct().collect {
            trySend(it)
        }
        awaitClose { channel.close() }
    }

    override suspend fun addUser(user: User) {
        withContext(ioDispatcher) {
            localDataSource.addUser(user)
        }
    }

    override suspend fun getUser(): User =
        withContext(ioDispatcher) {
            localDataSource.getUser()
        }
}
