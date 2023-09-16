package com.bahadir.tostbangcase.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.presentation.screens.login.state.LoginState
import com.bahadir.tostbangcase.presentation.screens.login.state.LoginUiEvent
import com.bahadir.tostbangcase.presentation.screens.login.state.ScreenName
import com.bahadir.tostbangcase.presentation.theme.FiriyaTheme
import com.bahadir.tostbangcase.presentation.util.ButtonComponent
import com.bahadir.tostbangcase.presentation.util.ClickableLoginTextComponent
import com.bahadir.tostbangcase.presentation.util.DividerTextComponent
import com.bahadir.tostbangcase.presentation.util.HeadingTextComponent
import com.bahadir.tostbangcase.presentation.util.MyTextFieldComponent
import com.bahadir.tostbangcase.presentation.util.NormalTextComponent

@Composable
fun LoginScreen(navigateToHome: () -> Unit = {}, viewModel: LoginVM = hiltViewModel()) {
    val uiState by viewModel.state.collectAsState(initial = LoginState())

    LaunchedEffect(key1 = uiState.isSuccessful) {
        if (uiState.isSuccessful) {
            navigateToHome()
        }
    }

    LoginBody(uiState = uiState, uiEvent = viewModel)
}

@Composable
fun LoginBody(uiState: LoginState, uiEvent: LoginVM) {
    FiriyaTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.2f),
                ) {
                    NormalTextComponent(value = uiState.screenName.screenName)
                    HeadingTextComponent(value = "Welcome to Firiya")
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.4f),
                ) {
                    MyTextFieldComponent(
                        labelValue = "EMail",
                        painterResource = painterResource(id = R.drawable.ic_email),
                        errorStatus = uiState.errorState.emailOrMobileErrorState.hasError,
                        errorMessage = uiState.errorState.emailOrMobileErrorState.errorMessageStringResource,
                        onTextChanged = { uiEvent.setEvent(LoginUiEvent.EmailOrMobileChanged(it)) },
                    )
                    MyTextFieldComponent(
                        labelValue = "Passowrd",
                        painterResource = painterResource(id = R.drawable.ic_lock),
                        errorStatus = uiState.errorState.passwordErrorState.hasError,
                        errorMessage = uiState.errorState.passwordErrorState.errorMessageStringResource,
                        onTextChanged = { uiEvent.setEvent(LoginUiEvent.PasswordChanged(it)) },
                    )

                    when (uiState.screenName) {
                        ScreenName.REGISTER -> {
                            MyTextFieldComponent(
                                labelValue = "Name",
                                painterResource = painterResource(id = R.drawable.ic_people),
                                errorStatus = uiState.errorState.nameErrorState.hasError,
                                errorMessage = uiState.errorState.nameErrorState.errorMessageStringResource,
                                onTextChanged = { uiEvent.setEvent(LoginUiEvent.NameChange(it)) },
                            )
                        }

                        else -> Unit
                    }
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.4f),
                ) {
                    ButtonComponent(
                        value = uiState.screenName.screenName,
                        onButtonClicked = {
                            uiEvent.setEvent(LoginUiEvent.SendUser(uiState.screenName))
                        },
                    )

                    DividerTextComponent()
                    ClickableLoginTextComponent(tryingToLogin = (uiState.screenName == ScreenName.REGISTER)) {
                        uiEvent.setEvent(LoginUiEvent.ChangeScreen(uiState.screenName))
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    ButtonComponent(
                        value = "Continue",
                        onButtonClicked = {
                            uiEvent.setEvent(LoginUiEvent.SendUser(ScreenName.NONE))
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).width(150.dp),
                    )
                }
            }
        }
    }
}
