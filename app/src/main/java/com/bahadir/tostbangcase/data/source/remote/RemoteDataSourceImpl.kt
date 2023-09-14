package com.bahadir.tostbangcase.data.source.remote

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.api.FiriyaApi
import com.bahadir.tostbangcase.data.model.FiriyaItem

class RemoteDataSourceImpl(private val firiyaApi: FiriyaApi) : RemoteDataSource {
    override suspend fun getProduct(): Resource<List<FiriyaItem>> {
        return try {
            Resource.Success(firiyaApi.getFiriyaItems())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
