package com.bahadir.tostbangcase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

@Dao
interface FiriyaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: FiriyaUI)

    @Query("SELECT * FROM firiyaItem")
    suspend fun getProducts(): List<FiriyaUI>

    @Query("DELETE FROM firiyaItem WHERE id = :productID")
    suspend fun deleteProduct(productID: String)
}
