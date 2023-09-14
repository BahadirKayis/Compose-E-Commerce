package com.bahadir.tostbangcase.di

import com.bahadir.tostbangcase.data.model.FiriyaItem
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.mapper.FiriyaListMapper
import com.bahadir.tostbangcase.domain.mapper.FiriyaProductEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MapperModule {
    @Binds
    @ViewModelScoped
    abstract fun bindTopAnimeEntityMapper(animeEntityMapper: FiriyaProductEntityMapper): FiriyaListMapper<FiriyaItem, FiriyaUI>
}
