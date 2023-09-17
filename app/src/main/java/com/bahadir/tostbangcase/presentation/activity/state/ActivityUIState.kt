package com.bahadir.tostbangcase.presentation.activity.state

import com.bahadir.tostbangcase.delegation.ees.State

data class ActivityUIState(val networkStatus: Boolean = false) : State
