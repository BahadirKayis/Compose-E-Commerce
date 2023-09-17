package com.bahadir.tostbangcase.core.extensions

import android.util.Patterns
import java.util.regex.Pattern

fun Double.formatPrice(): String {
    val formattedValue = if (this == this.toInt().toDouble()) {
        String.format("%.0f", this)
    } else {
        String.format("%.2f", this)
    }
    return formattedValue
}

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    val passwordREGEX = Pattern.compile(
        "^" + "(?=.*[a-zA-Z])" + // any letter
            ".{4,}" + // at least 4 characters
            "$",
    )
    return passwordREGEX.matcher(this).matches()
}
