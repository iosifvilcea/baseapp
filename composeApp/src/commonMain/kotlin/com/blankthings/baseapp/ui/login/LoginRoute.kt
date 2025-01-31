package com.blankthings.baseapp.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.navigation.NavActions

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navActions: NavActions
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    loginViewModel.reset()

    when (uiState) {
        AuthUiState.Default -> {
            LoginScreen(
                authUiState = uiState,
                onForgotAccountClicked = navActions.navigateToForgotPassword,
                onLoginClicked = loginViewModel::login,
                onCreateAccountClicked = navActions.navigateToCreateAccount,
            )
        }
        AuthUiState.Loading -> {
            /*TODO*/
        }
        AuthUiState.LoginSuccess -> navActions.navigateToAuthorized.invoke()
    }
}