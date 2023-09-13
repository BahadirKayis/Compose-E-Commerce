package com.bahadir.tostbangcase.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bahadir.tostbangcase.data.model.FiriyaItem

@Dao
interface FiriyaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: FiriyaItem)

    @Query("SELECT * FROM firiyaItem")
    suspend fun getProducts(): List<FiriyaItem>

    @Query("DELETE FROM firiyaItem WHERE id = :productID")
    suspend fun deleteProduct(productID: String)
}
