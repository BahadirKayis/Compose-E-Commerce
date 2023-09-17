package com.bahadir.tostbangcase.presentation.screens.shoppinghistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.usecase.history.SoldHistoryUseCase
import com.bahadir.tostbangcase.domain.usecase.user.get.GetUserUseCase
import com.bahadir.tostbangcase.presentation.screens.shoppinghistory.state.SoldUIEvent
import com.bahadir.tostbangcase.presentation.screens.shoppinghistory.state.SoldUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoldHistoryVM @Inject constructor(
    private val soldHistory: SoldHistoryUseCase,
    private val getUser: GetUserUseCase,
) : ViewModel(),
    VMDelegation<SoldUIEvent, SoldUIState> by VMDelegationImpl(SoldUIState()) {
    init {
        viewModel(this)
        getUserCheck()
    }

    private fun getSoldHistory() {
        viewModelScope.launch {
            soldHistory.invoke().collect {
                setState(getCurrentState().copy(soldHistory = it, isLogin = true))
            }
        }
    }

    private fun getUserCheck() {
        viewModelScope.launch {
            val user = getUser.invoke()
            Log.e("user", user.toString())
            user?.let {
                getSoldHistory()
            } ?: setState(getCurrentState().copy(isLogin = false))
        }
    }
}
