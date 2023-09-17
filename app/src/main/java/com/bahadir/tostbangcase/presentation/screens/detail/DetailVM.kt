package com.bahadir.tostbangcase.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.core.Constants
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCase
import com.bahadir.tostbangcase.presentation.screens.detail.state.DetailUIEvent
import com.bahadir.tostbangcase.presentation.screens.detail.state.DetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addProductUseCase: AddProductUseCase,
) : ViewModel(),
    VMDelegation<DetailUIEvent, DetailUIState> by VMDelegationImpl(DetailUIState(isLoading = true)) {
    init {
        viewModel(this)
        viewModelScope.launch {
            event.collectLatest {
                when (it) {
                    is DetailUIEvent.AddProduct -> {
                        addProduct(it.product)
                    }
                }
            }
        }

        productDetail()
    }

    private fun productDetail() {
        savedStateHandle.get<FiriyaUI>(key = Constants.DETAIL)?.let {
            setState(getCurrentState().copy(isLoading = false, uiData = it))
        }
    }

    private fun addProduct(product: FiriyaUI) {
        viewModelScope.launch {
            addProductUseCase(product)
        }
    }
}
