package com.bahadir.tostbangcase.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCase
import com.bahadir.tostbangcase.presentation.screens.home.state.HomeErrorState
import com.bahadir.tostbangcase.presentation.screens.home.state.HomeUIEvent
import com.bahadir.tostbangcase.presentation.screens.home.state.HomeUIState
import com.bahadir.tostbangcase.presentation.screens.home.state.homeNetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val getProductUseCase: GetProductUseCase) :
    ViewModel(),
    VMDelegation<HomeUIEvent, HomeUIState> by VMDelegationImpl(HomeUIState(isLoading = true)) {

    init {
        viewModel(this)
        event.onEach {
            when (it) {
                is HomeUIEvent.NavigateDetail -> {
                    setState(getCurrentState().copy(navigateToDetail = true))
                }
            }
        }.launchIn(viewModelScope)
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductUseCase().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        setState(getCurrentState().copy(isLoading = false, products = it.data))
                    }

                    is Resource.Error -> {
                        setState(
                            getCurrentState().copy(
                                errorState = HomeErrorState(networkError = homeNetworkError), isLoading = false
                            ),
                        )
                    }
                }
            }
        }
    }
}
