package com.bahadir.tostbangcase.di

import com.bahadir.tostbangcase.data.repository.FiriyaRepository
import com.bahadir.tostbangcase.data.repository.FiriyaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun provideFiriyaRepository(
        firiyaRepository: FiriyaRepositoryImpl,
    ): FiriyaRepository
}

@InstallIn(ViewModelComponent::class)
@Module
object CoroutineDispatchersModule {

    @Provides
    @ViewModelScoped
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
