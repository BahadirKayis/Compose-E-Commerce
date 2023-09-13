package com.bahadir.tostbangcase.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bahadir.tostbangcase.presentation.screens.login.components.NormalTextComponents

@Composable
fun LoginScreen(navigeteToHome: (Unit) -> Unit = {}, viewModel: LoginVM = hiltViewModel()) {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Text(
            text = "LoginScreen",
            Modifier.fillMaxSize(),

        )
        NormalTextComponents(value = "LoginScreen")
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
