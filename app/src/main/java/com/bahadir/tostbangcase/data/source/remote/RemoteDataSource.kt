package com.bahadir.tostbangcase.data.source.remote

import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.data.model.FiriyaItem

fun interface RemoteDataSource {
    suspend fun getProduct(): Resource<List<FiriyaItem>>
}
