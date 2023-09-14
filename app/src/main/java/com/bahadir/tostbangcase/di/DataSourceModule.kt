package com.bahadir.tostbangcase.di

import com.bahadir.tostbangcase.data.api.FiriyaApi
import com.bahadir.tostbangcase.data.source.local.LocalDataSource
import com.bahadir.tostbangcase.data.source.local.LocalDataSourceImpl
import com.bahadir.tostbangcase.data.source.remote.RemoteDataSource
import com.bahadir.tostbangcase.data.source.remote.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(firiyaApi: FiriyaApi): RemoteDataSource =
        RemoteDataSourceImpl(firiyaApi)

    @Provides
    @Singleton
    fun provideLocalDataSource(): LocalDataSource =
        LocalDataSourceImpl() // LocalDataSource'ın uygun bir uygulamasını oluşturun veya sağlayın.
}
