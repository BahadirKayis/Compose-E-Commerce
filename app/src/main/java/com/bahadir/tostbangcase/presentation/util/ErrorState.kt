package com.bahadir.tostbangcase.presentation.util

import androidx.annotation.StringRes

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int? = null,
)
