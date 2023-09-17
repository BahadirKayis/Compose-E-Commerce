package com.bahadir.tostbangcase.delegation.viewmodel

import androidx.lifecycle.ViewModel
import com.bahadir.tostbangcase.delegation.ees.Event
import com.bahadir.tostbangcase.delegation.ees.State
import kotlinx.coroutines.flow.SharedFlow

interface VMDelegation<EVENT : Event, STATE : State> {
    fun viewModel(viewModel: ViewModel)

    fun setEvent(event: EVENT)
    fun setState(state: STATE)
    fun getCurrentState(): STATE

    val event: SharedFlow<EVENT>
    val state: SharedFlow<STATE>
}
