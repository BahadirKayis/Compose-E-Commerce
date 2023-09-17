package com.bahadir.tostbangcase.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.core.DataObserve
import com.bahadir.tostbangcase.core.extensions.collectIn
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.network.NetworkObserver
import com.bahadir.tostbangcase.presentation.activity.state.ActivityUIEvent
import com.bahadir.tostbangcase.presentation.activity.state.ActivityUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ActivityVM @Inject constructor(
    networkObserver: NetworkObserver,
    @Named(value = "Unconfined") private val dispatcherUnconfined: CoroutineDispatcher,
) : ViewModel(),
    VMDelegation<ActivityUIEvent, ActivityUIState> by VMDelegationImpl(ActivityUIState()) {
    private val dataObserve = DataObserve(
        executeProcess = {
            setState(state = ActivityUIState(networkStatus = true))
        },
        executeProgress = {
            setState(state = ActivityUIState(networkStatus = false))
        },
    )

    init {
        viewModel(this)
        networkObserver.observe()
            .flowOn(context = dispatcherUnconfined)
            .collectIn(coroutineScope = viewModelScope, function = dataObserve::observeData)
    }
}
