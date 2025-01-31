package com.blankthings.baseapp.ui

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.navigation.Routes

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navigateToRoute: (Routes) -> Unit,
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    loginViewModel.reset()

    when (uiState) {
        AuthUiState.Default -> {
            LoginScreen(
                authUiState = uiState,
                onForgotAccountClicked = { navigateToRoute.invoke(Routes.ForgotPassword) },
                onLoginClicked = loginViewModel::login,
                onCreateAccountClicked = { navigateToRoute.invoke(Routes.CreateAccount) },
            )
        }
        AuthUiState.Loading -> {
            /*TODO*/
        }
        AuthUiState.LoginSuccess -> navigateToRoute.invoke(Routes.Authorized)
    }
}