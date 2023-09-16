package com.bahadir.tostbangcase.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bahadir.tostbangcase.core.Converters
import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

@Database(
    entities = [FiriyaUI::class, FiriyaSoldBasket::class, User::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class FiriyaDatabase : RoomDatabase() {

    abstract fun firiyaDao(): FiriyaDao
}
