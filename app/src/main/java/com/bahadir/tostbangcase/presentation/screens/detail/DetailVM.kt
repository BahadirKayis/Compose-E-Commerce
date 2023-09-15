package com.bahadir.tostbangcase.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.core.Constants
import com.bahadir.tostbangcase.domain.entitiy.FiriyaUI
import com.bahadir.tostbangcase.domain.usecase.add.AddProductUseCase
import com.bahadir.tostbangcase.presentation.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addProductUseCase: AddProductUseCase,
) : ViewModel() {
    private val _screenState =
        MutableStateFlow<ScreenState<FiriyaUI>>(value = ScreenState.Loading)
    val screenState: Flow<ScreenState<FiriyaUI>> get() = _screenState

    init {
        firiyaDetail()
    }

    private fun firiyaDetail() {
        savedStateHandle.get<FiriyaUI>(key = Constants.DETAIL)?.let {
            _screenState.value = ScreenState.Success(it)
        }
    }

    fun addFiriya(firiyaUI: FiriyaUI) {
        viewModelScope.launch {
            addProductUseCase(firiyaUI)
        }
    }
}
