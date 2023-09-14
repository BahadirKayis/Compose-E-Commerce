package com.bahadir.tostbangcase.di

import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCase
import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun provideGetProductUseCase(
        getProductUseCase: GetProductUseCaseImpl,
    ): GetProductUseCase
}
