package com.bahadir.tostbangcase.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI

@Database(
    entities = [FiriyaUI::class],
    version = 1,
    exportSchema = false,
)

abstract class FiriyaDatabase : RoomDatabase() {

    abstract fun firiyaDao(): FiriyaDao
}
