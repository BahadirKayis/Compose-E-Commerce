package com.bahadir.tostbangcase.presentation.screens.shoppinghistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.domain.entitiy.FiriyaSoldBasket
import com.bahadir.tostbangcase.domain.usecase.history.SoldHistoryUseCase
import com.bahadir.tostbangcase.presentation.util.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoldHistoryVM @Inject constructor(private val soldHistory: SoldHistoryUseCase) : ViewModel() {
    private val _screenState =
        MutableStateFlow<ScreenState<List<FiriyaSoldBasket>>>(value = ScreenState.Loading)
    val screenState: Flow<ScreenState<List<FiriyaSoldBasket>>> get() = _screenState

    init {
        getSoldHistory()
    }

    private fun getSoldHistory() {
        viewModelScope.launch {
            soldHistory.invoke().collect {
                _screenState.value = ScreenState.Success(it)
            }
        }
    }
}
