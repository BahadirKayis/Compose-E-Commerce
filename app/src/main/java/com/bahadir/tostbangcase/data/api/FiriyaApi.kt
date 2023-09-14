package com.bahadir.tostbangcase.data.api

import com.bahadir.tostbangcase.core.Constants.PRODUCTS
import com.bahadir.tostbangcase.data.model.FiriyaItem
import retrofit2.http.GET

fun interface FiriyaApi {
    @GET(PRODUCTS)
    suspend fun getFiriyaItems(): List<FiriyaItem>
}
