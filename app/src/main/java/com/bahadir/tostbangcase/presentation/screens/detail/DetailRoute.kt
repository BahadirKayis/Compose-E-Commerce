package com.bahadir.tostbangcase.presentation.screens.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailRoute(viewModel: DetailVM = hiltViewModel()) {
    Text(text = "DetailRoute")
}
