package com.bahadir.tostbangcase.core.extensions

fun Double.formatPrice(): String {
    val formattedValue = if (this == this.toInt().toDouble()) {
        // Noktadan sonraki kısmı 0 ise tam sayı olarak kabul edin.
        String.format("%.0f", this)
    } else {
        // Noktadan sonraki kısmı 0'dan farklı ise normal olarak formatlayın.
        String.format("%.2f", this)
    }
    return formattedValue
}
