package com.bahadir.tostbangcase.presentation.util

import androidx.annotation.StringRes

sealed class ScreenState<out T : Any> {
    data object Loading : ScreenState<Nothing>()
    data class Error(@StringRes val message: Int) : ScreenState<Nothing>()
    data class Success<out T : Any>(val uiData: T) : ScreenState<T>()
}
