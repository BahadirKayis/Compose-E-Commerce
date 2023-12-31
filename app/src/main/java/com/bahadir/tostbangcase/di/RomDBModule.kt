package com.bahadir.tostbangcase.di

import android.content.Context
import androidx.room.Room
import com.bahadir.tostbangcase.data.room.FiriyaDao
import com.bahadir.tostbangcase.data.room.FiriyaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RomDBModule {

    @Provides
    @Singleton
    fun provideFiriyaRoomDB(@ApplicationContext appContext: Context): FiriyaDatabase =
        Room.databaseBuilder(
            appContext,
            FiriyaDatabase::class.java,
            "productDatabase.db",
        ).build()

    @Provides
    @Singleton
    fun provideFiriyaDAO(firiyaDao: FiriyaDatabase): FiriyaDao =
        firiyaDao.firiyaDao()
}
