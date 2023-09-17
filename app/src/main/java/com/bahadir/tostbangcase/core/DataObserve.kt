package com.bahadir.tostbangcase.core

import com.bahadir.tostbangcase.domain.network.NetworkStatus

class DataObserve(var executeProcess: () -> Unit, var executeProgress: () -> Unit) {

    fun observeData(status: NetworkStatus) {
        when (status) {
            NetworkStatus.AVAILABLE -> executeProcess()
            else -> executeProgress()
        }
    }
}
