package com.bahadir.tostbangcase.presentation.screens.basket

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bahadir.tostbangcase.data.model.FiriyaItem

@Composable
fun BasketRoute(navigateToDetail: (FiriyaItem) -> Unit) {
    Text(text = "BasketRoute", color = Color.Black)
}
