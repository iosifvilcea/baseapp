package com.blankthings.baseapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.navigation.NavActions

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navActions: NavActions
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is AuthUiState.Loading -> {
            LoginScreen(
                authUiState = uiState,
                onForgotAccountClicked = navActions.navigateToForgotPassword,
                onLoginClicked = loginViewModel::login,
                onCreateAccountClicked = navActions.navigateToCreateAccount,
            )
            Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)), // Semi-transparent overlay
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        is AuthUiState.Default -> {
            LoginScreen(
                authUiState = uiState,
                onForgotAccountClicked = navActions.navigateToForgotPassword,
                onLoginClicked = loginViewModel::login,
                onCreateAccountClicked = navActions.navigateToCreateAccount,
            )
        }
        is AuthUiState.Success -> navActions.navigateToAuthorized.invoke()
        is AuthUiState.Failure -> { println("Aw shiiiiiiiiiii.") }
    }
}