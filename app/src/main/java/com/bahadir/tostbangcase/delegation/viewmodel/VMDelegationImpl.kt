package com.bahadir.tostbangcase.delegation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.animelist.presentation.base.ees.Event
import com.bahadir.tostbangcase.delegation.ees.State
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class VMDelegationImpl<EVENT : Event, STATE : State>(
    setInitialState: STATE,
) : VMDelegation<EVENT, STATE> {
    private lateinit var viewModel: ViewModel

    private val _eventTemp = MutableSharedFlow<EVENT>()
    override val event: SharedFlow<EVENT>
        get() = _eventTemp.asSharedFlow()

    private val _stateTemp = MutableStateFlow(setInitialState)
    override val state: SharedFlow<STATE>
        get() = _stateTemp.asSharedFlow()

    override fun viewModel(viewModel: ViewModel) {
        this.viewModel = viewModel
    }

    override fun getCurrentState() = _stateTemp.value
    override fun setState(state: STATE) {
        viewModel.viewModelScope.launch {
            _stateTemp.emit(state)
        }
    }

    override fun setEvent(event: EVENT) {
        viewModel.viewModelScope.launch {
            _eventTemp.emit(event)
        }
    }

}
