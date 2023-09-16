package com.bahadir.tostbangcase.presentation.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bahadir.tostbangcase.R
import com.bahadir.tostbangcase.data.model.User
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegation
import com.bahadir.tostbangcase.delegation.viewmodel.VMDelegationImpl
import com.bahadir.tostbangcase.domain.usecase.user.add.AddUserUseCase
import com.bahadir.tostbangcase.domain.usecase.user.get.GetUserUseCase
import com.bahadir.tostbangcase.presentation.screens.login.state.ErrorState
import com.bahadir.tostbangcase.presentation.screens.login.state.LoginErrorState
import com.bahadir.tostbangcase.presentation.screens.login.state.LoginState
import com.bahadir.tostbangcase.presentation.screens.login.state.LoginUiEvent
import com.bahadir.tostbangcase.presentation.screens.login.state.ScreenName
import com.bahadir.tostbangcase.presentation.screens.login.state.emailOrMobileEmptyErrorState
import com.bahadir.tostbangcase.presentation.screens.login.state.passwordEmptyErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getUser: GetUserUseCase,
) :
    ViewModel(),
    VMDelegation<LoginUiEvent, LoginState> by VMDelegationImpl(LoginState()) {
    init {
        viewModel(this)
        viewModelScope.launch {
            event.collectLatest { event ->
                when (event) {
                    is LoginUiEvent.SendUser -> {
                        loginOrSave(screenState = event.screenState)
                    }

                    is LoginUiEvent.ChangeScreen -> {
                        if (event.screenState == ScreenName.LOGIN) {
                            setState(getCurrentState().copy(screenName = ScreenName.REGISTER))
                        } else {
                            setState(getCurrentState().copy(screenName = ScreenName.LOGIN))
                        }
                    }

                    is LoginUiEvent.EmailOrMobileChanged -> {
                        emailChange(event.inputValue)
                    }

                    is LoginUiEvent.PasswordChanged -> {
                        passWordChange(event.inputValue)
                    }

                    is LoginUiEvent.NameChange -> {
                        nameChange(event.inputValue)
                    }
                }
            }
        }
        userCheck()
    }

    private fun userCheck() {
        viewModelScope.launch {
            getUser.invoke()?.let { setState(getCurrentState().copy(isSuccessful = true)) }
        }
    }

    private fun loginOrSave(screenState: ScreenName) {
        when (screenState) {
            ScreenName.LOGIN -> {
                if (!validateInputs()) {
                    return
                }
                viewModelScope.launch {
                    getUser.invoke()?.let {
                        val email = getCurrentState().emailOrMobile
                        val password = getCurrentState().emailOrMobile
                        if (it.email == email && it.password == password) {
                            setState(getCurrentState().copy(isSuccessful = true))
                        }
                    }
                    setState(
                        getCurrentState().copy(
                            errorState = LoginErrorState(
                                emailOrMobileErrorState = ErrorState(
                                    hasError = true,
                                    errorMessageStringResource = R.string.not_correct,
                                ),
                                passwordErrorState = ErrorState(
                                    hasError = true,
                                    errorMessageStringResource = R.string.not_correct,
                                ),
                            ),
                        ),
                    )
                }
            }

            ScreenName.REGISTER -> {
                if (!validateInputs()) {
                    return
                }
                if (!validateInputName()) {
                    return
                }
                val user = getCurrentState().let {
                    User(
                        name = it.name,
                        email = it.emailOrMobile,
                        password = it.password,
                    )
                }
                viewModelScope.launch { addUserUseCase.invoke(user) }
                setState(getCurrentState().copy(isSuccessful = true))
            }

            ScreenName.NONE -> {
                setState(getCurrentState().copy(isSuccessful = true))
            }
        }
    }

    private fun validateInputName(): Boolean {
        val name = getCurrentState().name.trim()
        return when {
            // Email/Mobile empty
            name.isEmpty() -> {
                setState(
                    getCurrentState().copy(
                        errorState = LoginErrorState(
                            emailOrMobileErrorState = emailOrMobileEmptyErrorState,
                        ),
                    ),
                )
                false
            }
            // No errors
            else -> {
                // Set default error state
                setState(
                    getCurrentState().copy(errorState = LoginErrorState()),
                )
                true
            }
        }
    }

    private fun emailChange(inputValue: String) {
        setState(
            getCurrentState().copy(
                emailOrMobile = inputValue,
                errorState = getCurrentState().errorState.copy(
                    emailOrMobileErrorState = if (inputValue.trim().isNotEmpty()) {
                        ErrorState()
                    } else {
                        emailOrMobileEmptyErrorState
                    },
                ),
            ),
        )
    }

    private fun passWordChange(inputValue: String) {
        setState(
            getCurrentState().copy(
                password = inputValue,
                errorState = getCurrentState().errorState.copy(
                    passwordErrorState = if (inputValue.trim().isNotEmpty()) {
                        ErrorState()
                    } else {
                        passwordEmptyErrorState
                    },
                ),
            ),
        )
    }

    private fun nameChange(inputValue: String) {
        setState(
            getCurrentState().copy(
                name = inputValue,
                errorState = getCurrentState().errorState.copy(
                    emailOrMobileErrorState = if (getCurrentState().name.trim().isNotEmpty()) {
                        ErrorState()
                    } else {
                        emailOrMobileEmptyErrorState
                    },
                ),
            ),
        )
    }

    private fun validateInputs(): Boolean {
        val emailOrMobileString = getCurrentState().emailOrMobile.trim()
        val passwordString = getCurrentState().password.trim()
        return when {
            // Email/Mobile empty
            emailOrMobileString.isEmpty() -> {
                setState(
                    getCurrentState().copy(
                        errorState = LoginErrorState(
                            emailOrMobileErrorState = emailOrMobileEmptyErrorState,
                        ),
                    ),
                )
                false
            }

            // Password Empty
            passwordString.isEmpty() -> {
                setState(
                    getCurrentState().copy(
                        errorState = LoginErrorState(
                            passwordErrorState = passwordEmptyErrorState,
                        ),
                    ),
                )
                false
            }

            // No errors
            else -> {
                // Set default error state
                true
            }
        }
    }
}
