package com.bahadir.tostbangcase.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bahadir.tostbangcase.presentation.screens.login.components.NormalTextComponents

@Composable
fun HomeRoute() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "HomeRoute")
    }
    NormalTextComponents(value = "LoginScreen")
}
