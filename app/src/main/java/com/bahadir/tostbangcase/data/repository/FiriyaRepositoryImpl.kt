package com.bahadir.tostbangcase.data.repository

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.data.source.local.LocalDataSource
import com.bahadir.tostbangcase.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
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

    override suspend fun insertProduct(product: List<FiriyaItem>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct() {
        TODO("Not yet implemented")
    }

    override suspend fun getBasket(): Resource<List<FiriyaItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getShoppingHistory(): Resource<List<FiriyaItem>> {
        TODO("Not yet implemented")
    }
}
