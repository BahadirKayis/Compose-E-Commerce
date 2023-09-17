package com.bahadir.tostbangcase.presentation.screens.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCase
import com.bahadir.tostbangcase.domain.usecase.entityproducts.GetEntityProductUseCase
import com.bahadir.tostbangcase.domain.usecase.remove.RemoveProductUseCase
import com.bahadir.tostbangcase.domain.usecase.sold.SoldBasketProductUseCase
import com.bahadir.tostbangcase.presentation.screens.basket.state.BasketUIEvent
import com.bahadir.tostbangcase.presentation.screens.basket.state.BasketUIState
import com.bahadir.tostbangcase.presentation.screens.basket.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketVM @Inject constructor(
    private val getProduct: GetEntityProductUseCase,
    private val addProduct: AddProductUseCase,
    private val removeProduct: RemoveProductUseCase,
    private val soldBasketUseCase: SoldBasketProductUseCase,
) : ViewModel(), VMDelegation<BasketUIEvent, BasketUIState> by VMDelegationImpl(BasketUIState()) {
    init {
        viewModel(this)
        viewModelScope.launch {
            event.collect { event ->
                when (event) {
                    is BasketUIEvent.UpdateProduct -> updateProduct(event.product, event.state)

                    is BasketUIEvent.BottomSheetBasketSold -> {
                        getCurrentState().uiData?.let { soldBasketProduct(it) }
                        setState(getCurrentState().copy(navigateToHomeCardName = event.cardInfo))
                    }
                }
            }
        }
        getBasket()
    }

    private fun updateProduct(product: FiriyaUI, state: ProductState) {
        viewModelScope.launch {
            when (state) {
                ProductState.ADD -> addProduct(product)
                ProductState.REMOVE -> removeProduct.removeProduct(product)
            }
        }
    }

    private fun getBasket() {
        viewModelScope.launch {
            getProduct.getProduct().collectLatest {
                setState((getCurrentState().copy(uiData = it)))
            }
        }
    }

    private fun soldBasketProduct(product: List<FiriyaUI>) {
        viewModelScope.launch {
            soldBasketUseCase.invoke(product)
            removeProduct.removeAllProduct(product)
        }
    }
}
