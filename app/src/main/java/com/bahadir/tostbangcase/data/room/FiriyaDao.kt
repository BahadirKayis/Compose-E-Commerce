package com.bahadir.tostbangcase.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import kotlinx.coroutines.flow.Flow

@Dao
interface FiriyaDao {
    @Query("SELECT * FROM firiyaItem")
    fun getProducts(): Flow<List<FiriyaUI>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: FiriyaUI)

    @Delete
    suspend fun deleteAllProduct(product: List<FiriyaUI>)

    @Update
    suspend fun updateProduct(product: FiriyaUI)

    @Query("SELECT * FROM firiyaItem WHERE id = :productID")
    suspend fun findProductById(productID: Int): FiriyaUI?

    @Query("DELETE FROM firiyaItem WHERE id = :productID")
    suspend fun deleteProduct(productID: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSoldHistory(product: FiriyaSoldBasket)

    @Query("SELECT * FROM firiyaSoldBasket")
    suspend fun getSoldHistory(): List<FiriyaSoldBasket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): User
}
