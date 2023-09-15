package com.bahadir.tostbangcase.presentation.screens.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.core.ProductState
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCase
import com.bahadir.tostbangcase.domain.usecase.entityproducts.GetEntityProductUseCase
import com.bahadir.tostbangcase.domain.usecase.remove.RemoveProductUseCase
import com.bahadir.tostbangcase.domain.usecase.sold.SoldBasketProductUseCase
import com.bahadir.tostbangcase.presentation.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketVM @Inject constructor(
    private val getProduct: GetEntityProductUseCase,
    private val addProduct: AddProductUseCase,
    private val removeProduct: RemoveProductUseCase,
    private val soldUseCase: SoldBasketProductUseCase,

) :
    ViewModel() {
    private val _screenState =
        MutableStateFlow<ScreenState<List<FiriyaUI>>>(value = ScreenState.Loading)
    val screenState: Flow<ScreenState<List<FiriyaUI>>> get() = _screenState

    init {
        getBasket()
    }

    fun updateProduct(product: FiriyaUI, state: ProductState) {
        viewModelScope.launch {
            when (state) {
                ProductState.ADD -> addProduct(product)
                ProductState.REMOVE -> removeProduct.removeProduct(product)
            }
        }
    }

    private fun getBasket() {
        getProduct.getProduct().onEach {
            _screenState.value = ScreenState.Success(it)
        }.launchIn(viewModelScope)
    }

    fun soldProduct(firiyaUI: List<FiriyaUI>) {
        viewModelScope.launch {
            soldUseCase.invoke(firiyaUI)
            removeProduct.removeProduct(firiyaUI)
        }
    }
}
