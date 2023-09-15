package com.bahadir.tostbangcase.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.core.Resource
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.usecase.products.GetProductUseCase
import com.bahadir.tostbangcase.presentation.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val getProductUseCase: GetProductUseCase) : ViewModel() {
    private val _screenState =
        MutableStateFlow<ScreenState<List<FiriyaUI>>>(value = ScreenState.Loading)
    val screenState: Flow<ScreenState<List<FiriyaUI>>> get() = _screenState

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductUseCase().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _screenState.value = ScreenState.Success(it.data)
                    }

                    is Resource.Error -> {
                        _screenState.value = ScreenState.Error(R.string.error_message)
                    }

                    else -> Unit
                }
            }
        }
    }
}
