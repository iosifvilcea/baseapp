package com.blankthings.baseapp.ui

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    onCreateAccountClick: () -> Unit,
    onForgotAccountClick: () -> Unit,
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        homeUiState = uiState,
        onCreateAccountClick = onCreateAccountClick,
        onForgotPasswordClick = onForgotAccountClick
    )
}