package com.bahadir.tostbangcase.di

import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCase
import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.entityproducts.GetEntityProductUseCase
import com.bahadir.tostbangcase.domain.usecase.entityproducts.GetEntityProductUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.history.SoldHistoryUseCase
import com.bahadir.tostbangcase.domain.usecase.history.SoldHistoryUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCase
import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.remove.RemoveProductUseCase
import com.bahadir.tostbangcase.domain.usecase.remove.RemoveProductUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.sold.SoldBasketProductUseCase
import com.bahadir.tostbangcase.domain.usecase.sold.SoldBasketProductUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.user.add.AddUserUseCase
import com.bahadir.tostbangcase.domain.usecase.user.add.AddUserUseCaseImpl
import com.bahadir.tostbangcase.domain.usecase.user.get.GetUserUseCase
import com.bahadir.tostbangcase.domain.usecase.user.get.GetUserUseCaseImpl
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

    @Binds
    @ViewModelScoped
    abstract fun provideAddProductUseCase(
        addProductUseCase: AddProductUseCaseImpl,
    ): AddProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideSoldBasketProductUseCase(
        soldBasketProductUseCase: SoldBasketProductUseCaseImpl,
    ): SoldBasketProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetEntityProductUseCase(
        soldBasketProductUseCase: GetEntityProductUseCaseImpl,

    ): GetEntityProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideRemoveEntityProductUseCase(
        addUseCase: RemoveProductUseCaseImpl,

    ): RemoveProductUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideSoldHistoryEntityProductUseCase(
        soldUseCase: SoldHistoryUseCaseImpl,
    ): SoldHistoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideAddUser(
        soldUseCase: AddUserUseCaseImpl,
    ): AddUserUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideGetUser(
        soldUseCase: GetUserUseCaseImpl,
    ): GetUserUseCase
}
