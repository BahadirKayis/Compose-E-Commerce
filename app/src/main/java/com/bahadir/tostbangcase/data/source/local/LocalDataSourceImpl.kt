package com.bahadir.tostbangcase.data.source.local

import com.bahadir.tostbangcase.data.model.FiriyaItem

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun getProduct(): List<FiriyaItem> {
        TODO("Not yet implemented")
    }

    override suspend fun insertProduct(product: List<FiriyaItem>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct() {
        TODO("Not yet implemented")
    }
}
