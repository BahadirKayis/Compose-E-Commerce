package com.bahadir.tostbangcase.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bahadir.tostbangcase.core.Converters
import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.data.model.Rating

@Database(
    entities = [FiriyaItem::class, Rating::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class FiriyaDatabase : RoomDatabase() {

    abstract fun firiyaDao(): FiriyaDao
}
